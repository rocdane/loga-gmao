package tech.loga.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserResourceTest {

    @Autowired
    private UserManagement userManagement;

    @Test
    void register() {
        String token = userManagement.registerUser(new UserRegisterRequest(
                "admin",
                "P@$$w0rd",
                "ADMIN"
        ));
        System.out.println(token);
        Assertions.assertNotNull(token,"Failed to registrate user");
    }

    @Test
    void find() {
        String token = userManagement.getUserByName("super");
        System.out.println(token);
        Assertions.assertNotNull(
                token,
                "Failed to find user"
        );
    }

    @Test
    void allUser() {
        Assertions.assertNotNull(userManagement.getAllUser().get(0));
    }
}