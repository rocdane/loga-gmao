package tech.loga.dossier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customer-service")
public class DossierController {

    @Autowired
    private DossierManagement dossierManagement;

    @PostMapping(path = "/dossiers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dossier> create(@RequestBody Dossier dossier){
        dossier.setReference(dossier.getAutomobile().getNumber()+"-"+dossier.getAutomobile().getVin());
        Dossier created = dossierManagement.createDossier(dossier);
        if(created!=null){
            return ResponseEntity.ok(created);
        }else {
            throw new DossierRegistrationFailedException("Failed to registrate dossier");
        }
    }

    @GetMapping(path = "/dossiers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Dossier>> readAll(){
        return ResponseEntity.ok(dossierManagement.getAllDossier());
    }

    @GetMapping(path = "/dossiers/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Dossier>> readAllByClient(@PathVariable String name){
        List<Dossier> dossiers = dossierManagement.getAllDossierByClientName(name);
        if(dossiers.isEmpty()){
            throw new DossierNotFoundException(String.format("Impossible de trouver un dossier correspondants à : %s",name));
        }
        return ResponseEntity.ok(dossiers);
    }

    @GetMapping(path = "/dossiers/number/search/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Dossier>> readAllByAutomobile(@PathVariable String number){
        return ResponseEntity.ok(dossierManagement.getAllDossierByAutomobileNumber(number));
    }

    @GetMapping(path = "/dossiers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dossier read(@PathVariable Long id){
        return dossierManagement.getDossierById(id);
    }

    @GetMapping(path = "/dossiers/number/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dossier readByAutomobile(@PathVariable String number){
        return dossierManagement.getDossierByAutomobileNumber(number);
    }

    @GetMapping(path = "/dossiers/reference/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dossier readByReference(@PathVariable String reference){
        return dossierManagement.getDossierByReference(reference);
    }

    @PutMapping(path = "/dossiers/{id}")
    public void edit(@RequestBody Dossier dossier, Long id){
        dossierManagement.editDossier(dossier,id);
    }

    @DeleteMapping(path = "/dossiers/{id}")
    public void delete(@PathVariable Long id){
        dossierManagement.deleteDossier(id);
    }
}
