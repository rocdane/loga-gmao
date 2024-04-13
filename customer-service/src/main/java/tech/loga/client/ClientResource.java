package tech.loga.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientResource implements ClientManagement{

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Transactional
    public Client createClient(Client client) {
        if(clientRepository.findByContact(client.getContact())!=null)
            return null;
        else
            return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        Client client = null;
        if(clientRepository.findById(id).isPresent())
            client = clientRepository.findById(id).get();
        return client;
    }

    @Override
    public Client getClientByName(String name) {
        Client client = null;
        if(clientRepository.findByName(name).isPresent())
            client = clientRepository.findByName(name).get();
        return client;
    }

    @Override
    @Transactional
    public void editClient(Client client, Long id) {
        clientRepository
                .findById(id) // returns Optional<User>
                .ifPresent(up -> {
                    up.setName(client.getName());
                    up.setAddress(client.getAddress());
                    up.setContact(client.getContact());
                    up.setType(client.getType());
                    up.setLegalNotice(client.getLegalNotice());
                    clientRepository.saveAndFlush(up);
                });
    }

    @Override
    @Transactional
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
