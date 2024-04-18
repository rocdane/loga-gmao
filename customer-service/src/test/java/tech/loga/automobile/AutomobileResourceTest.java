package tech.loga.automobile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AutomobileResourceTest {

    @Autowired
    private AutomobileManagement automobileManagement;
    private final Automobile AUTOMOBILE = new Automobile(
            "AA-443-BC",
            "WBA7EYFG8736529FX",
            "MERCEDES",
            "C300",
            10000,
            "Mi"
    );

    @Test
    void createAutomobile() {
        Assertions.assertNotNull(automobileManagement.createAutomobile(AUTOMOBILE));
    }

    @Test
    void getAllAutomobile() {
        Assertions.assertNotNull(automobileManagement.getAllAutomobile().get(0));
    }

    @Test
    void getAutomobileById() {
        Assertions.assertNotNull(automobileManagement.getAutomobileById(1L));
    }

    @Test
    void getAutomobileByNumber() {
        Assertions.assertNotNull(automobileManagement.getAutomobileByNumber(AUTOMOBILE.getNumber()));
    }

    @Test
    void getAutomobileByVin() {
        Assertions.assertNotNull(automobileManagement.getAutomobileByVin(AUTOMOBILE.getVin()));
    }
}