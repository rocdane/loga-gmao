package tech.loga.diagnosis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiagnosisManagementTest {

    @Autowired
    private DiagnosisManagement diagnosisManagement;

    private final DiagnosisRequest REQUEST = new DiagnosisRequest(
            "CUSTOMERJSKMSOPSQNCJUEDK",
            "EMPLOYEE",
            10000,
            "NEW DESCRIPTION",
            new ArrayList<>()
    );

    @Test
    void createDiagnosis() {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setCustomer(REQUEST.customer());
        diagnosis.setEmployee(REQUEST.employee());
        diagnosis.setDescription(REQUEST.description());
        diagnosis.setMileage(REQUEST.mileage());
        diagnosis.getFactors().add(new Factor(null,"MOTEUR","BRUIT MOTEUR","RÉPARATION DU MOTEUR"));
        diagnosisManagement.createDiagnosis(diagnosis);
        Assertions.assertNotNull(diagnosis.getId());
    }

    @Test
    void getDiagnosisById() {
        Assertions.assertNotNull(diagnosisManagement.getDiagnosisById(1L));
    }

    @Test
    void getDiagnosisByReference() {
    }

    @Test
    void getAllDiagnosis() {
        Assertions.assertNotNull(diagnosisManagement.getAllDiagnosis().get(0));
    }
}