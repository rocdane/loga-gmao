package tech.loga.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableHystrix
public class App {

    @Autowired
    private Logger logger;
    @Autowired
    private Filter filter;

    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner init() {
        return  args -> {
            logger.info("LoGA Gateway Server Initialization ---");
        };
    }

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p
                        .path("/get")
                        .filters(f-> f
                                .addRequestHeader("MyHeader","MyURI")
                                .addRequestParameter("Param", "MyValue")
                                .addRequestHeader("Access-Control-Allow-Origin","*")
                                .addRequestHeader("Access-Control-Allow-Headers","Accept, Content-Type"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p
                        .path("/authentication-service/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                .addRequestHeader("Access-Control-Allow-Origin","*")
                                .addRequestHeader("Access-Control-Allow-Headers","Accept, Content-Type"))
                        .uri("lb://authentication-service"))
                .route(p -> p
                        .path("/customer-service/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                .addRequestHeader("Access-Control-Allow-Origin","*")
                                .addRequestHeader("Access-Control-Allow-Headers","Accept, Content-Type"))
                        .uri("lb://customer-service"))
                .route(p -> p
                        .path("/maintenance-service/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                .addRequestHeader("Access-Control-Allow-Origin","*")
                                .addRequestHeader("Access-Control-Allow-Headers","Accept, Content-Type"))
                        .uri("lb://maintenance-service"))
                .route(p -> p
                        .path("/intelligent-service/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                .addRequestHeader("Access-Control-Allow-Origin","*")
                                .addRequestHeader("Access-Control-Allow-Headers","Accept, Content-Type"))
                        .uri("lb://intelligent-service"))
                .route(p -> p
                        .path("/reporting-service/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                .addRequestHeader("Access-Control-Allow-Origin","*")
                                .addRequestHeader("Access-Control-Allow-Headers","Accept, Content-Type"))
                        .uri("lb://reporting-service"))
                .build();
    }

    @Bean
    public CorsWebFilter corsFilter(){
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
