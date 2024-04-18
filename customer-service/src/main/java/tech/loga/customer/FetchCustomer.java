package tech.loga.customer;

import tech.loga.client.Client;
import tech.loga.dossier.Dossier;

import java.util.List;

public interface FetchCustomer {
    List<Client> getAllClient();
    Dossier getDossierById(Long id);
    Dossier getDossierByReference(String reference);
    Dossier getDossierByAutomobileNumber(String number);
    List<Dossier> getAllDossier();
    List<Dossier> getAllDossierByClientName(String name);
    List<Dossier> getAllDossierByAutomobileNumber(String number);
}
