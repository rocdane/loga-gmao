package tech.loga.vendor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Slf4j
@Configuration
@EnableHystrix
public class App {

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Bean
    public CommandLineRunner init() {
        return  args -> {
            log.info("LoGA Gateway Server Initialization ---");
        };
    }

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p
                        .path("/authentication-service/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://authentication-service"))
                .route(p -> p
                        .path("/customer-service/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://customer-service"))
                .build();
    }

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
        corsConfiguration.addAllowedHeader("Content-Type, Accept");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(source);
    }
}
