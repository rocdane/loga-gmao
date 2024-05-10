package tech.loga.app.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.loga.app.factory.AuthSession;

@FeignClient("gateway-server")
public interface AuthenticationService {
    @GetMapping(path = "/authentication-service/session/{token}",produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthSession checkSession(@PathVariable String token);
}
