package tech.loga.reception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class ReceptionManagementTest {

    @Autowired
    private ReceptionManagement receptionManagement;

    private final ReceptionRequest request = new ReceptionRequest(
            "CUSTOMERMLDJFKDIEOPMLSD",
            "EMPLOYEE",
            1000,
            "Our new description of reception",
            new ArrayList<>()
    );

    @Test
    void createReception() {
        Reception reception = new Reception();
        reception.setCustomer(request.customer());
        reception.setEmployee(request.employee());
        reception.setMileage(request.mileage());
        reception.setDescription(request.description());
        reception.getNotices().add(new Notice(null,"CHK001","Controle général","Défaillance générale"));
        receptionManagement.createReception(reception);
        Assertions.assertNotNull(reception.getId());
    }

    @Test
    void getReceptionById() {
        Assertions.assertNotNull(receptionManagement.getReceptionById(1L));
    }

    @Test
    void getAllReception() {
        Assertions.assertNotNull(receptionManagement.getAllReception().get(0));
    }
}