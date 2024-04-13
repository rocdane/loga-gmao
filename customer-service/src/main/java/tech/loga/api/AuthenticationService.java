package tech.loga.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tech.loga.auth.Credentials;
import tech.loga.auth.Passport;

@FeignClient(name = "gateway-server")
public interface AuthenticationService {
    @GetMapping(path = "/authentication-service/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    Passport authenticate(@RequestBody Credentials credentials);
}
