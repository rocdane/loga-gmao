package tech.loga.user;

import io.jsonwebtoken.lang.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.loga.auth.Credentials;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserResourceTest {

    @Autowired
    UserResource userResource;

    @Test
    void register() {
        User user = userResource.register(new UserRegisterRequest(
                "super",
                "P@$$w0rd",
                "SUPER"
        ));
        Assert.notNull(user,"Failed to registrate user");
    }

    @Test
    void find() {
        User user = userResource.find("super");
        Assert.notNull(
                user,
                "Failed to find user"
        );
    }

    @Test
    void allUser() {
        Assert.notEmpty(userResource.allUser());
    }
}