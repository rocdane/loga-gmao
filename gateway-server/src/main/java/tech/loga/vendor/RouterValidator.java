package tech.loga.vendor;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public static final Set<String> openApiEndpoints = new HashSet<>(List.of(
            "/eureka",
            "/authentication-service/**"
    ));

    public Predicate<ServerHttpRequest> isSecured =
            serverHttpRequest -> openApiEndpoints.stream()
                    .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}
