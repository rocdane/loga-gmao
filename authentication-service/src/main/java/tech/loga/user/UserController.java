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

@CrossOrigin
@RestController
@RequestMapping("/authentication-service")
public class UserController {

    @Autowired
    private UserManagement userManagement;

    private final String SERVICE = "authentication-service";

    @PostMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest userRegisterRequest){
        try {
            String token = userManagement.registerUser(userRegisterRequest);
            return ResponseEntity.ok(token);
        }catch (Exception e){
            throw new UserRegistrationFailedException("User registration failed.\n"+e.getMessage());
        }
    }

    @Retry(name = SERVICE, fallbackMethod = "userFallback")
    @Bulkhead(name = SERVICE, fallbackMethod = "userFallback")
    @RateLimiter(name = SERVICE, fallbackMethod = "userFallback")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "userFallback")
    @GetMapping(path = "/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> list() {
        try {
            return ResponseEntity.ok(userManagement.getAllUser());
        }catch (Exception e){
            throw new UserNotFoundException("Any user found :\n"+e.getMessage());
        }
    }

    @PutMapping(path = "/update/{id}")
    public void update(@RequestBody UserUpdateRequest userUpdateRequest, @PathVariable Long id){
        try {
            userManagement.editUser(userUpdateRequest,id);
        }catch (Exception e){
            throw new UserRegistrationFailedException("User registration failed.\n"+e.getMessage());
        }
    }

    @DeleteMapping(path = "/users/edit/{id}")
    public void delete(@PathVariable Long id){
        try {
            userManagement.deleteUser(id);
        }catch (Exception e){
            throw new UserNotFoundException(String.format("User with id : %d cannot be deleted \n%s",id,e.getMessage()));
        }
    }

    public String userFallback(Exception e){
        return String.format("Failed to get users resource : \n%s",e.getMessage());
    }
}
