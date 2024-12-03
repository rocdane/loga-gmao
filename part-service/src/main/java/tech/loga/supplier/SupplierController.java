package tech.loga.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("part-service")
public class SupplierController {

    @Autowired
    private SupplierManagement supplierManagement;

    @PostMapping(path = "/suppliers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Supplier> registerSupplier(@RequestBody Supplier supplier){
        return ResponseEntity.ok(supplierManagement.registerSupplier(supplier));
    }

    @GetMapping(path = "/suppliers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Supplier>> getSuppliers(){
        return ResponseEntity.ok(supplierManagement.getAllSupplier());
    }

    @GetMapping(path = "/suppliers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Supplier> readSupplier(@PathVariable Long id){
        return ResponseEntity.ok(supplierManagement.getSupplier(id));
    }

    @PutMapping(path = "/suppliers/{id}")
    public void editSupplier(@RequestBody Supplier supplier, @PathVariable Long id){
        supplierManagement.editSupplier(supplier,id);
    }

    @DeleteMapping(path = "/suppliers/{id}")
    public void deleteSupplier(@PathVariable Long id){
        supplierManagement.deleteSupplier(id);
    }
}
