package tech.loga.user;

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

    @PostMapping(path = "/registrate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest userRegisterRequest){
        String token = userManagement.registerUser(userRegisterRequest);
        if (token != null){
            return ResponseEntity.ok("User registrate successfully");
        }else
            throw new AuthenticationErrorException("User registration failed");
    }

    @GetMapping(path = "/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> list() {
        return ResponseEntity.ok(userManagement.getAllUser());
    }

    @PutMapping(path = "/update/{id}")
    public void update(@RequestBody UserUpdateRequest userUpdateRequest, @PathVariable Long id){
        userManagement.editUser(userUpdateRequest,id);
    }

    @DeleteMapping(path = "/users/{id}")
    public void delete(@PathVariable Long id){
        userManagement.deleteUser(id);
    }
}
