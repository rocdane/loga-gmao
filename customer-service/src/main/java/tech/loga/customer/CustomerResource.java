package tech.loga.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.loga.automobile.Automobile;
import tech.loga.automobile.AutomobileManagement;
import tech.loga.client.Client;
import tech.loga.client.ClientManagement;
import tech.loga.dossier.Dossier;
import tech.loga.dossier.DossierManagement;

import java.util.List;

@Service
public class CustomerResource implements RegistrateCustomer, FetchCustomer, UpdateCustomer {

    private final DossierManagement dossierManagement;
    private final ClientManagement clientManagement;
    private final AutomobileManagement automobileManagement;

    @Autowired
    public CustomerResource(DossierManagement dossierManagement,
                            ClientManagement clientManagement,
                            AutomobileManagement automobileManagement
    ) {
        this.dossierManagement = dossierManagement;
        this.clientManagement = clientManagement;
        this.automobileManagement = automobileManagement;
    }

    @Override
    public Dossier createCustomer(Dossier dossier) {
        return dossierManagement.createDossier(dossier);
    }

    @Override
    public Dossier createCustomer(Client client, Automobile automobile) {
        return null;
    }

    @Override
    public List<Client> getAllClient() {
        return clientManagement.getAllClient();
    }

    @Override
    public Dossier getDossierById(Long id) {
        return dossierManagement.getDossierById(id);
    }

    @Override
    public Dossier getDossierByReference(String reference) {
        return dossierManagement.getDossierByReference(reference);
    }

    @Override
    public Dossier getDossierByAutomobileNumber(String number) {
        return dossierManagement.getDossierByAutomobileNumber(number);
    }

    @Override
    public List<Dossier> getAllDossier() {
        return dossierManagement.getAllDossier();
    }

    @Override
    public List<Dossier> getAllDossierByClientName(String name) {
        return dossierManagement.getAllDossierByClientName(name);
    }

    @Override
    public List<Dossier> getAllDossierByAutomobileNumber(String number) {
        return dossierManagement.getAllDossierByAutomobileNumber(number);
    }

    @Override
    public void editDossier(Dossier dossier, Long id) {
        dossierManagement.editDossier(dossier, id);
    }

    @Override
    public void editAutomobile(Automobile automobile, Long id) {
        automobileManagement.editAutomobile(automobile, id);
    }

    @Override
    public void editClient(Client client, Long id) {
        clientManagement.editClient(client, id);
    }

    @Override
    public void archiveCustomer(Long id) {
        Dossier dossier = dossierManagement.getDossierById(id);
        dossier.setArchived(true);
        dossierManagement.editDossier(dossier, id);
    }

    @Override
    public void deleteCustomer(Long id) {

    }
}
