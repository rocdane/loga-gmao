package tech.loga.user;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import tech.loga.auth.AuthenticationErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/authentication-service")
public class UserController {

    @Autowired
    private UserManagement userManagement;

    private final String SERVICE = "authentication-service";

    @PostMapping(path = "/registrate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest userRegisterRequest){
        String token = userManagement.registerUser(userRegisterRequest);
        if (token != null){
            return ResponseEntity.ok("User registration success.");
        }else{
            throw new AuthenticationErrorException("User registration failed.");
        }
    }

    @Retry(name = SERVICE, fallbackMethod = "userFallback")
    @Bulkhead(name = SERVICE, fallbackMethod = "userFallback")
    @RateLimiter(name = SERVICE, fallbackMethod = "userFallback")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "userFallback")
    @GetMapping(path = "/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> list() {
        return ResponseEntity.ok(userManagement.getAllUser());
    }

    @PutMapping(path = "/update/{id}")
    public void update(@RequestBody UserUpdateRequest userUpdateRequest, @PathVariable Long id){
        userManagement.editUser(userUpdateRequest,id);
    }

    @DeleteMapping(path = "/users/edit/{id}")
    public void delete(@PathVariable Long id){
        userManagement.deleteUser(id);
    }

    public String userFallback(Exception e){
        return String.format("Failed to get users resource : \n%s",e.getMessage());
    }
}
