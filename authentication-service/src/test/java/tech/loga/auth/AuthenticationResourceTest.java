package tech.loga.auth;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class AuthenticationResourceTest {

    @Autowired
    private AuthenticationManagement authenticationManagement;

    @Test
    void authenticate() {
        String token =
                authenticationManagement
                        .authenticate(new AuthenticationRequest("super","P@$$w0rd"));
        Assertions.assertNotNull(token);
        boolean auth = authenticationManagement.authenticate(token);
        log.info(String.format("Authenticated session : %s",token));
        Assertions.assertTrue(auth);
    }
}