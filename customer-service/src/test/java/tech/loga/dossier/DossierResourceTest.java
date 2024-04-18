package tech.loga.dossier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.loga.automobile.Automobile;
import tech.loga.client.Client;
import tech.loga.customer.CustomerBuilder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DossierResourceTest {

    @Autowired
    private DossierManagement dossierManagement;

    private final Automobile AUTOMOBILE = new Automobile(
            "AA-443-BC",
            "WBA7EYFG8736529FX",
            "MERCEDES",
            "C300",
            10000,
            "Mi"
    );

    private final Client CLIENT = new Client(
            "LOGA GMAO",
            "COMPANY",
            "SAS",
            "loga.gmao",
            "contact@loga.gmao"
    );

    private final Dossier DOSSIER = new CustomerBuilder().build(CLIENT, AUTOMOBILE);

    @Test
    void createDossier() {
        Assertions.assertNotNull(dossierManagement.createDossier(DOSSIER));
    }

    @Test
    void getAllDossier() {
        Assertions.assertNotNull(dossierManagement.getAllDossier().get(0));
    }

    @Test
    void getAllDossierByClientName() {
        Assertions.assertNotNull(dossierManagement.getAllDossierByClientName(CLIENT.getName()).get(0));
    }

    @Test
    void getAllDossierByAutomobileNumber() {
        Assertions.assertNotNull(dossierManagement.getAllDossierByAutomobileNumber(AUTOMOBILE.getNumber()).get(0));
    }

    @Test
    void getDossierById() {
        Assertions.assertNotNull(dossierManagement.getDossierById(1L));
    }

    @Test
    void getDossierByReference() {
        Assertions.assertNotNull(dossierManagement.getDossierByReference(DOSSIER.getReference()));
    }

    @Test
    void getDossierByAutomobileNumber() {
        Assertions.assertNotNull(dossierManagement.getDossierByAutomobileNumber(AUTOMOBILE.getNumber()));
    }
}