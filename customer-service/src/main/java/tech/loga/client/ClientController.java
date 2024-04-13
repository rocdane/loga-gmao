package tech.loga.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customer-service")
public class ClientController {

    @Autowired
    private ClientManagement clientManagement;

    @PostMapping(path = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
    public Client newClient(@RequestBody Client client){
        return clientManagement.createClient(client);
    }

    @GetMapping(path = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> readAll(){
        return clientManagement.getAllClient();
    }

    @GetMapping(path = "/clients/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Client read(@PathVariable Long id){
        Client client = clientManagement.getClientById(id);
        if(client==null){
            throw new ClientNotFoundException(String.format("Impossible de trouver le client avec la clé : %d",id));
        }else {
            return client;
        }
    }

    @GetMapping(path = "/clients/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Client readByName(@PathVariable String name){
        Client client = clientManagement.getClientByName(name);
        if(client==null){
            throw new ClientNotFoundException(String.format("Impossible de trouver les infos du client avec la clé : %s",name));
        }else {
            return client;
        }
    }

    @PutMapping(path = "/clients/{id}")
    public void edit(@RequestBody Client client, @PathVariable Long id){
        clientManagement.editClient(client,id);
    }

    @DeleteMapping(path = "/clients/{id}")
    public void delete(@PathVariable Long id){
        clientManagement.deleteClient(id);
    }
}
