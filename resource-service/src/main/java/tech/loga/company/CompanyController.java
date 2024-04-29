package tech.loga.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.loga.asset.AssetManagement;

@CrossOrigin
@RestController
@RequestMapping("/enterprise-service")
public class CompanyController {

    private final CompanyManagement companyManagement;
    private final AssetManagement assetsService;

    @Autowired
    public CompanyController(CompanyManagement companyManagement,
                             AssetManagement assetsService) {
        this.companyManagement = companyManagement;
        this.assetsService = assetsService;
    }

    @PostMapping(path = "/company", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> register(@RequestBody Company company){
        Company created = companyManagement.createCompany(company);
        return ResponseEntity.ok(created);
    }

    @GetMapping(path = "/company/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> read(@PathVariable Long id){
        Company company = companyManagement.getCompanyById(id);
        return ResponseEntity.ok(company);
    }

    @PutMapping(path = "/company/{id}")
    public void edit(@RequestBody Company company, @PathVariable Long id){
        companyManagement.editCompany(company, id);
    }

    @DeleteMapping(path = "/company/{id}")
    public void delete(@PathVariable Long id){
        companyManagement.deleteCompany(id);
    }
}
