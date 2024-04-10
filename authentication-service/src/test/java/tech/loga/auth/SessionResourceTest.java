package tech.loga.auth;

import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SessionResourceTest {

    @Autowired
    SessionResource sessionResource;

    @Test
    void authenticate() {
        Passport passport = sessionResource.authenticate(new Credentials("super","P@$$w0rd"));
        log.info("Authenticated session : "+passport.toString());
        Assert.notNull(passport);
    }
}