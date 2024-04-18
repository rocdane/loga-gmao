package tech.loga.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("gateway-server")
public interface AuthenticationService {
    @GetMapping(path = "/authentication-service/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> authenticate(@RequestParam("token") String token);
}
