package tech.loga.client;

import java.util.List;

public interface ClientManagement {
    Client createClient(Client client);
    List<Client> getAllClient();
    Client getClientById(Long id);
    Client getClientByName(String name);
    void editClient(Client client, Long id);
    void deleteClient(Long id);
}
