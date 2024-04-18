package tech.loga.customer;

import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.loga.automobile.Automobile;
import tech.loga.automobile.AutomobileManagement;
import tech.loga.client.Client;
import tech.loga.client.ClientManagement;
import tech.loga.dossier.Dossier;
import tech.loga.dossier.DossierManagement;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customer-service")
public class CustomerController {

    private final RegistrateCustomer registrateCustomer;
    private final FetchCustomer fetchCustomer;
    private final UpdateCustomer updateCustomer;
    private final CustomerMapper customerMapper;
    private final CustomerBuilder customerBuilder;

    @Autowired
    public CustomerController(RegistrateCustomer registrateCustomer,
                              FetchCustomer fetchCustomer,
                              UpdateCustomer updateCustomer,
                              CustomerMapper customerMapper, CustomerBuilder customerBuilder) {
        this.registrateCustomer = registrateCustomer;
        this.fetchCustomer = fetchCustomer;
        this.updateCustomer = updateCustomer;
        this.customerMapper = customerMapper;
        this.customerBuilder = customerBuilder;
    }

    @PostMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Dossier dossier = registrateCustomer.createCustomer(customerBuilder.build(customer));
        if(dossier!=null){
            return ResponseEntity
                    .status(HttpStatus.SC_CREATED)
                    .body(customerMapper.apply(dossier));
        }
        throw new CustomerRegistrationFailedException("Failed to registrate dossier");
    }

    @GetMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> customers =
                fetchCustomer
                        .getAllDossier()
                        .stream()
                        .map(customerMapper)
                        .toList();
        if(customers.isEmpty()){
            throw new CustomerNotFoundException("Any customer found!");
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping(path = "/customers/clients", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getAllClient(){
        List<Client> clients = fetchCustomer.getAllClient();
        if(clients.isEmpty()){
            throw new CustomerNotFoundException("Any customer found");
        }
        return ResponseEntity.ok(clients);
    }

    @GetMapping(path = "/customers/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getAllCustomerByName(@PathVariable String name){
        List<Customer> customers =
                fetchCustomer
                        .getAllDossierByClientName(name)
                        .stream()
                        .map(customerMapper)
                        .toList();
        if(customers.isEmpty()){
            throw new CustomerNotFoundException(String.format("Any Customer found with name : %s",name));
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping(path = "/customer/number/search/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getAllCustomerByNumber(@PathVariable String number){
        List<Customer> customers =
                fetchCustomer
                        .getAllDossierByAutomobileNumber(number)
                        .stream()
                        .map(customerMapper)
                        .toList();
        if(customers.isEmpty()){
            throw new CustomerNotFoundException(String.format("Any customer found with number : %s",number));
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping(path = "/customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
        Dossier dossier = fetchCustomer.getDossierById(id);
        if(dossier!=null){
            return ResponseEntity.ok(customerMapper.apply(dossier));
        }
        throw new CustomerNotFoundException(String.format("Any customer found with id : %d",id));
    }

    @GetMapping(path = "/customers/client/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerByNumber(@PathVariable String number){
        Dossier dossier = fetchCustomer.getDossierByAutomobileNumber(number);
        if(dossier!=null){
            return ResponseEntity.ok(customerMapper.apply(dossier));
        }
        throw new CustomerNotFoundException(String.format("Any Customer found with number : %s",number));
    }

    @GetMapping(path = "/customers/dossier/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerByReference(@PathVariable String reference){
        Dossier dossier = fetchCustomer.getDossierByReference(reference);
        if(dossier!=null){
            return ResponseEntity.ok(customerMapper.apply(dossier));
        }
        throw new CustomerNotFoundException(String.format("Any Customer found with reference : %s",reference));
    }

    @PutMapping(path = "/dossiers/{id}")
    public void editCustomer(@RequestBody Dossier dossier, Long id){
        try {
            updateCustomer.editDossier(dossier, id);
        }catch (Exception e){
            throw new CustomerRegistrationFailedException(String.format("Failed to update customer data : %s",id));
        }
    }

    @PutMapping(path = "/customers/clients/{id}")
    public void editClient(@RequestBody Client client, @PathVariable Long id){
        try {
            updateCustomer.editClient(client, id);
        }catch (Exception e){
            throw new CustomerRegistrationFailedException(String.format("Failed to update customer data : \n%s",e.getMessage()));
        }
    }

    @PutMapping(path = "/customers/automobiles/{id}")
    public void editAutomobile(@RequestBody Automobile automobile, @PathVariable Long id){
        try {
            updateCustomer.editAutomobile(automobile, id);
        }catch (Exception e){
            throw new CustomerRegistrationFailedException(String.format("Failed to update customer data : \n%s",e.getMessage()));
        }
    }

    @PutMapping(path = "/customers/archive/{id}")
    public void archiveCustomer(@PathVariable Long id){
        try {
            updateCustomer.archiveCustomer(id);
        }catch (Exception e){
            throw new CustomerNotFoundException(String.format("Failed to archive customer : \n%s",e.getMessage()));
        }
    }

    @DeleteMapping(path = "/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        try {
            updateCustomer.deleteCustomer(id);
        }catch (Exception e){
            throw new CustomerNotFoundException(String.format("Failed to delete customer : \n%s",e.getMessage()));
        }
    }
}
