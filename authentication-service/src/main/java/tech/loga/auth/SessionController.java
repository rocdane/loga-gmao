package tech.loga.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/authentication-service")
public class SessionController {

    @Autowired
    private IPassport passport;

    @PostMapping(path = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Passport> authenticate(@RequestBody Credentials credentials) {
        try {
            return ResponseEntity.ok(passport.authenticate(credentials));
        }catch (Exception e){
            throw new SessionErrorException("Failed to establish session: \n"+e.getMessage());
        }
    }
}
