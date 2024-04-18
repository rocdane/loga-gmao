package tech.loga.vendor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import tech.loga.api.AuthenticationService;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Component
@RefreshScope
public class AuthenticationFilter implements GatewayFilter {

    private final RouterValidator routerValidator;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationFilter(RouterValidator routerValidator,
                                AuthenticationService authenticationService) {
        this.routerValidator = routerValidator;
        this.authenticationService = authenticationService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        log.info("Path of the request received -> {}", request.getPath());

        if(routerValidator.isSecured.test(request)){

            if(this.isAuthMissing(request)){
                log.error(HttpStatus.UNAUTHORIZED.toString());
                return this.onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            String authHeader = this.getAuthHeader(request);

            if(authHeader!=null && authHeader.startsWith("Bearer ")){
                authHeader = authHeader.substring(7);
            }

            final String token = authenticationService.authenticate(authHeader).getBody();

            if(token==null || token.isEmpty()){
                log.error(HttpStatus.FORBIDDEN.toString());
                return this.onError(exchange, HttpStatus.FORBIDDEN);
            }

            this.updateRequest(exchange, token);
        }

        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request){
        return request.getHeaders().getOrEmpty(AUTHORIZATION).get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request){
        return !request.getHeaders().containsKey(AUTHORIZATION);
    }

    private void updateRequest(ServerWebExchange exchange, String token){
        exchange
                .getRequest()
                .mutate()
                .header("authenticated", token).build();
    }
}
