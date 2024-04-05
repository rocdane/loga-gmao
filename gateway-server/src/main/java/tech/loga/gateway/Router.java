package tech.loga.gateway;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class Router {

    public static final List<String> openApiEndpoints = List.of(
            "/eureka",
            "/authentication-service/**",
            "/customer-service/**",
            "/maintenance-service/**",
            "/reporting-service/**",
            "/finance-service/**",
            "/part-service/**",
            "/resource-service/**"
    );

    public Predicate<ServerHttpRequest> isSecured =
            serverHttpRequest -> openApiEndpoints.stream()
                    .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}
