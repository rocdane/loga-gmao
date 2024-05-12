package tech.loga.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("part-service")
public class StoreController {

    @Autowired
    private StoreManagement storeManagement;

    @PostMapping(path = "products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> registerProduct(@RequestBody Product product){
        try {
            return ResponseEntity.ok(this.storeManagement.registerProduct(product));
        }catch (Exception e){
            throw new ProductRegistrationFailedException(String.format("Product registration failed : \n%s",e.getMessage()));
        }
    }

    @GetMapping(path = "products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(this.storeManagement.getProduct(id));
        }catch (Exception e){
            throw new ProductNotFoundException(String.format("Product with id : %d not found : \n%s",id, e.getMessage()));
        }
    }

    @GetMapping(path = "products/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductByReference(@PathVariable String reference){
        try {
            return ResponseEntity.ok(this.storeManagement.getProduct(reference));
        }catch (Exception e){
            throw new ProductNotFoundException(String.format("Product with reference : %s not found : \n%s",reference, e.getMessage()));
        }
    }

    @GetMapping(path = "products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProduct(){
        try {
            return ResponseEntity.ok(this.storeManagement.getAllProduct());
        }catch (Exception e){
            throw new ProductNotFoundException(String.format("Any product found : \n%s",e.getMessage()));
        }
    }

    @GetMapping(path = "products/{reference}")
    public ResponseEntity<List<Product>> getAllProductByReference(@PathVariable String reference){
        try {
            return ResponseEntity.ok(this.storeManagement.getAllProduct(reference));
        }catch (Exception e){
            throw new ProductNotFoundException(String.format("Any product with reference : %s found : \n%s",reference,e.getMessage()));
        }
    }

    @PutMapping(path = "products/{id}")
    public void editProduct(@RequestBody Product product, @PathVariable Long id){
        try {
            this.storeManagement.updateProduct(product,id);
        }catch (Exception e){
            throw new ProductRegistrationFailedException(String.format("Update product with id:%d failed : \n%s",id,e.getMessage()));
        }
    }

    @DeleteMapping(path = "products/{id}")
    public void deleteProduct(@PathVariable Long id){
        try {
            this.storeManagement.deleteProduct(id);
        }catch (Exception e){
            throw new ProductNotFoundException(String.format("Delete product with id:%d failed : \n%s",id,e.getMessage()));
        }
    }
}
