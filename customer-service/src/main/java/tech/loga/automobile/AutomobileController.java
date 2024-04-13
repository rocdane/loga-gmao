package tech.loga.automobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customer-service")
public class AutomobileController {

    @Autowired
    private AutomobileManagement automobileManagement;

    @PostMapping(path = "/automobiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public Automobile create(@RequestBody Automobile automobile){
        return automobileManagement.createAutomobile(automobile);
    }

    @GetMapping(path = "/automobiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Automobile> readAll(){
        return automobileManagement.getAllAutomobile();
    }

    @GetMapping(path = "/automobiles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Automobile read(@PathVariable Long id){
        return automobileManagement.getAutomobileById(id);
    }

    @GetMapping(path = "/automobiles/number/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Automobile readByNumber(@PathVariable String number){
        Automobile automobile = automobileManagement.getAutomobileByNumber(number);
        if (automobile==null){
            throw new AutomobileNotFoundException(String.format("Impossible de trouver un dossier correspondants à : %s",number));
        }
        return automobile;
    }

    @GetMapping(path = "/automobiles/vin/{vin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Automobile readByVin(@PathVariable String vin){
        return automobileManagement.getAutomobileByVin(vin);
    }

    @PutMapping(path = "/automobiles/{id}")
    public void edit(@RequestBody Automobile automobile, @PathVariable Long id){
        automobileManagement.editAutomobile(automobile,id);
    }

    @DeleteMapping(path = "/automobiles/{id}")
    public void delete(@PathVariable Long id){
        automobileManagement.deleteAutomobile(id);
    }
}
