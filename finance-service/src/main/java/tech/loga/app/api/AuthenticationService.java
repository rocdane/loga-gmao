package tech.loga.app.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("gateway-server")
public interface AuthenticationService {
    @GetMapping(path = "/authentication-service/validate",produces = MediaType.APPLICATION_JSON_VALUE)
    String authenticate(@RequestParam("token") String token);
}
