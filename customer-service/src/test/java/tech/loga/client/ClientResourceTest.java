package tech.loga.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientResourceTest {

    @Autowired
    private ClientManagement clientManagement;

    private final Client CLIENT = new Client(
            "LOGA GMAO",
            "COMPANY",
            "SAS",
            "loga.gmao",
            "contact@loga.gmao"
    );

    @Test
    void createClient() {
        Assertions.assertNotNull(clientManagement.createClient(CLIENT));
    }

    @Test
    void getAllClient() {
        Assertions.assertNotNull(clientManagement.getAllClient().get(0));
    }

    @Test
    void getClientById() {
        Assertions.assertNotNull(clientManagement.getClientById(1L));
    }

    @Test
    void getClientByName() {
        Assertions.assertNotNull(clientManagement.getClientByName(CLIENT.getName()));
    }
}