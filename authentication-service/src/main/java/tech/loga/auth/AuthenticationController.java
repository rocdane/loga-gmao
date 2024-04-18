package tech.loga.auth;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/authentication-service")
public class AuthenticationController {

    private static final String SERVICE = "authentication-service";

    @Autowired
    private AuthenticationManagement authenticationManagement;

    @Retry(name = SERVICE, fallbackMethod = "authenticateFallback")
    @Bulkhead(name = SERVICE, fallbackMethod = "authenticateFallback")
    @RateLimiter(name = SERVICE, fallbackMethod = "authenticateFallback")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "authenticateFallback")
    @PostMapping(path = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            return ResponseEntity.ok(authenticationManagement.authenticate(authenticationRequest));
        }catch (Exception e){
            throw new AuthenticationErrorException(String.format("Failed to establish session : \n%s",e.getMessage()));
        }
    }

    @Retry(name = SERVICE, fallbackMethod = "authenticateFallback")
    @Bulkhead(name = SERVICE, fallbackMethod = "authenticateFallback")
    @RateLimiter(name = SERVICE, fallbackMethod = "authenticateFallback")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "authenticateFallback")
    @GetMapping(path = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authenticate(@RequestParam("token") String token){
        if(authenticationManagement.authenticate(token)){
            return ResponseEntity.ok(token);
        }else{
            throw new AuthenticationErrorException("Authenticated session expired");
        }
    }

    public String authenticateFallback(Exception e){
        return String.format("Failed to authenticate session : \n%s",e.getMessage());
    }
}