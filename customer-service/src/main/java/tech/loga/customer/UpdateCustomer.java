package tech.loga.customer;

import tech.loga.automobile.Automobile;
import tech.loga.client.Client;
import tech.loga.dossier.Dossier;

public interface UpdateCustomer {
    void editDossier(Dossier dossier, Long id);
    void editAutomobile(Automobile automobile, Long id);
    void editClient(Client client, Long id);
    void archiveCustomer(Long id);
    void deleteCustomer(Long id);
}
