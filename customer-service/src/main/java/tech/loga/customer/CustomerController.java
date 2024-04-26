package tech.loga.customer;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.apache.http.HttpStatus;
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

    private static final String SERVICE = "customer-service";
    private final DossierManagement dossierManagement;
    private final ClientManagement clientManagement;
    private final AutomobileManagement automobileManagement;
    private final CustomerBuilder customerBuilder;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerController(DossierManagement dossierManagement,
                              ClientManagement clientManagement,
                              AutomobileManagement automobileManagement,
                              CustomerBuilder customerBuilder,
                              CustomerMapper customerMapper) {
        this.dossierManagement = dossierManagement;
        this.clientManagement = clientManagement;
        this.automobileManagement = automobileManagement;
        this.customerBuilder = customerBuilder;
        this.customerMapper = customerMapper;
    }

    @PostMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            Dossier dossier = dossierManagement.createDossier(customerBuilder.build(customer));
                return ResponseEntity
                        .status(HttpStatus.SC_CREATED)
                        .body(customerMapper.apply(dossier));

        }catch (Exception e){
            throw new CustomerRegistrationFailedException("Failed to registrate dossier \n"+e.getMessage());
        }
    }

    @PostMapping(path = "/customers/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> createCustomer(@RequestBody Automobile automobile,
                                                   @PathVariable Long id) {
        try {
            Client client = clientManagement.getClientById(id);
            Dossier dossier = dossierManagement.createDossier(customerBuilder.build(client, automobile));
            return ResponseEntity
                    .status(HttpStatus.SC_CREATED)
                    .body(customerMapper.apply(dossier));

        }catch (Exception e){
            throw new CustomerRegistrationFailedException("Customer registration Failed \n"+e.getMessage());
        }
    }

    @GetMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getAllCustomer(){
        try {
            List<Customer> customers =
                    dossierManagement
                            .getAllDossier()
                            .stream()
                            .map(customerMapper)
                            .toList();
            return ResponseEntity.ok(customers);
        }catch (Exception e){
            throw new CustomerNotFoundException("Any customer found \n"+e.getMessage());
        }
    }

    @GetMapping(path = "/customers/clients", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getAllClient(){
        try {
            List<Client> clients = clientManagement.getAllClient();
            return ResponseEntity.ok(clients);
        }catch (Exception e){
            throw new CustomerNotFoundException("Any customer found : \n"+e.getMessage());
        }
    }

    @GetMapping(path = "/customers/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getAllCustomerByName(@PathVariable String name){
        try {
            List<Customer> customers =
                    dossierManagement
                            .getAllDossierByClientName(name)
                            .stream()
                            .map(customerMapper)
                            .toList();
            return ResponseEntity.ok(customers);
        }catch (Exception e){
            throw new CustomerNotFoundException(String.format("Customer with name %s not found : \n%s",name, e.getMessage()));
        }
    }

    @GetMapping(path = "/customer/number/search/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getAllCustomerByNumber(@PathVariable String number){
        try {
            List<Customer> customers =
                    dossierManagement
                            .getAllDossierByAutomobileNumber(number)
                            .stream()
                            .map(customerMapper)
                            .toList();
            return ResponseEntity.ok(customers);
        }catch (Exception e){
            throw new CustomerNotFoundException(String.format("Customer with number %s not found : \n%s",number, e.getMessage()));
        }
    }

    @Retry(name = SERVICE, fallbackMethod = "customerFallback")
    @Bulkhead(name = SERVICE, fallbackMethod = "customerFallback")
    @RateLimiter(name = SERVICE, fallbackMethod = "customerFallback")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "customerFallback")
    @GetMapping(path = "/customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
        try {
            Dossier dossier = dossierManagement.getDossierById(id);
            return ResponseEntity.ok(customerMapper.apply(dossier));
        }catch (Exception e){
            throw new CustomerNotFoundException(String.format("Any customer found with id : %d",id));
        }
    }

    @Retry(name = SERVICE, fallbackMethod = "customerFallback")
    @Bulkhead(name = SERVICE, fallbackMethod = "customerFallback")
    @RateLimiter(name = SERVICE, fallbackMethod = "customerFallback")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "customerFallback")
    @GetMapping(path = "/customers/client/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerByNumber(@PathVariable String number){
        try {
            Dossier dossier = dossierManagement.getDossierByAutomobileNumber(number);
            return ResponseEntity.ok(customerMapper.apply(dossier));
        }catch (Exception e){
            throw new CustomerNotFoundException(String.format("Customer with number %s not found \n %s",number, e.getMessage()));
        }
    }

    @Retry(name = SERVICE, fallbackMethod = "customerFallback")
    @Bulkhead(name = SERVICE, fallbackMethod = "customerFallback")
    @RateLimiter(name = SERVICE, fallbackMethod = "customerFallback")
    @CircuitBreaker(name = SERVICE, fallbackMethod = "customerFallback")
    @GetMapping(path = "/customers/dossier/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerByReference(@PathVariable String reference){
        try {
            Dossier dossier = dossierManagement.getDossierByReference(reference);
            return ResponseEntity.ok(customerMapper.apply(dossier));
        }catch (Exception e){
            throw new CustomerNotFoundException(String.format("Customer with reference : %s not found \n%s",reference, e.getMessage()));
        }
    }

    @PutMapping(path = "/dossiers/{id}")
    public void editCustomer(@RequestBody Dossier dossier, Long id){
        try {
            dossierManagement.editDossier(dossier,id);
        }catch (Exception e){
            throw new CustomerRegistrationFailedException(String.format("Customer with id :%d registration failed : %s",id,e.getMessage()));
        }
    }

    @PutMapping(path = "/customers/clients/{id}")
    public void editClient(@RequestBody Client client, @PathVariable Long id){
        try {
            clientManagement.editClient(client,id);
        }catch (Exception e){
            throw new CustomerRegistrationFailedException(String.format("Failed to update customer data : \n%s",e.getMessage()));
        }
    }

    @PutMapping(path = "/customers/automobiles/{id}")
    public void editAutomobile(@RequestBody Automobile automobile, @PathVariable Long id){
        try {
            automobileManagement.editAutomobile(automobile, id);
        }catch (Exception e){
            throw new CustomerRegistrationFailedException(String.format("Failed to update customer data : \n%s",e.getMessage()));
        }
    }

    @PutMapping(path = "/customers/archive/{id}")
    public void archiveCustomer(@PathVariable Long id){
        try {
            dossierManagement.archiveDossier(id);
        }catch (Exception e){
            throw new CustomerNotFoundException(String.format("Failed to archive customer : \n%s",e.getMessage()));
        }
    }

    @DeleteMapping(path = "/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        try {
            dossierManagement.deleteDossier(id);
        }catch (Exception e){
            throw new CustomerNotFoundException(String.format("Failed to delete customer : \n%s",e.getMessage()));
        }
    }

    public String customerFallback(Exception e){
        return String.format("Failed to get customer resource : \n%s",e.getMessage());
    }
}
