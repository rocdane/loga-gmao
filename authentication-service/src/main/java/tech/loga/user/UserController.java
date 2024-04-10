package tech.loga.user;

import jakarta.servlet.http.HttpServletRequest;
import tech.loga.auth.SessionErrorException;
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
    private UserResource userResource;

    @PostMapping(path = "/registrate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@RequestBody UserRegisterRequest userRegisterRequest){
        User registered = userResource.register(userRegisterRequest);
        if (registered != null){
            return ResponseEntity.ok(registered);
        }else
            throw new SessionErrorException("User registration failed");
    }

    @GetMapping(path = "/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> list() {
        return ResponseEntity.ok(userResource.allUser());
    }

    @PutMapping(path = "/update/{id}")
    public void update(@RequestBody UserUpdateRequest userUpdateRequest, @PathVariable Long id){
        userResource.edit(userUpdateRequest,id);
    }

    @DeleteMapping(path = "/users/{id}")
    public void delete(@PathVariable Long id){
        userResource.delete(id);
    }
}
