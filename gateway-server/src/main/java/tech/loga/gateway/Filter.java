package tech.loga.gateway;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RefreshScope
@RequiredArgsConstructor
public class Filter extends OncePerRequestFilter implements GatewayFilter {

    private final Logger logger;
    private final Router router;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String jwt;
        final String token;

        if(authHeader==null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        token = jwtService.extractToken(jwt);

        if(token != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(token);
            if(jwtService.isTokenValid(jwt, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authHeader;
        String jwt;
        String token;
        ServerHttpRequest request = null;

        logger.info(String.format("Path of the request received -> {}", exchange.getRequest().getPath()));

        if(router.isSecured.test(exchange.getRequest())){
            try {
                authHeader = exchange.getRequest().getHeaders().get(AUTHORIZATION).get(0);

                if(authHeader!=null && authHeader.startsWith("Bearer ")){
                    jwt = authHeader.substring(7);
                    token = jwtService.extractToken(jwt);

                    if(token != null && SecurityContextHolder.getContext().getAuthentication() == null){

                        UserDetails userDetails = userDetailsService.loadUserByUsername(token);

                        if(jwtService.isTokenValid(jwt, userDetails)){

                            request = exchange.getRequest()
                                    .mutate()
                                    .header("authenticated",token).build();

                        }else {
                            return onError(exchange,HttpStatus.UNAUTHORIZED);
                        }
                    }
                }else {
                    return onError(exchange,HttpStatus.UNAUTHORIZED);
                }
            }catch (Exception e){
                throw new SessionErrorException("Unauthorized access");
            }
        }
        return chain.filter(exchange.mutate().request(request).build());
    }

    public Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
}
