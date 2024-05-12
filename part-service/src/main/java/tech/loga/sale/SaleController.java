package tech.loga.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("part-service")
public class SaleController {

    @Autowired
    private SaleManagement tradeManagement;

    @PostMapping(path = "sales", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sale> registerSale(Sale sale){
        try {
            return ResponseEntity.ok(this.tradeManagement.registerSale(sale));
        }catch (Exception e){
            throw new SaleRegistrationFailedException(String.format("Sale registration failed : \n%s",e.getMessage()));
        }
    }

    @GetMapping(path = "sales/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sale> getSale(@PathVariable Long id){
        try {
            return ResponseEntity.ok(this.tradeManagement.getSale(id));
        }catch (Exception e){
            throw new SaleNotFoundException(String.format("Sale with id:%d : \n%s",id,e.getMessage()));
        }
    }

    @GetMapping(path = "sales/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sale> getSale(@PathVariable String reference){
        try {
            return ResponseEntity.ok(this.tradeManagement.getSale(reference));
        }catch (Exception e){
            throw new SaleNotFoundException(String.format("Sale with reference:%s : \n%s",reference,e.getMessage()));
        }
    }

    @GetMapping(path = "sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sale>> getSales(){
        try {
            return ResponseEntity.ok(this.tradeManagement.getAllSale());
        }catch (Exception e){
            throw new SaleNotFoundException(String.format("Any Sale found : \n%s",e.getMessage()));
        }
    }

    @GetMapping(path = "sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sale>> getSales(@RequestParam("date") Date date){
        try {
            return ResponseEntity.ok(this.tradeManagement.getAllSale(date));
        }catch (Exception e){
            throw new SaleNotFoundException(String.format("Any sales registered on date : %s : \n%s",date,e.getMessage()));
        }
    }

    @GetMapping(path = "sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sale>> getSales(@RequestParam("start") Date start, @RequestParam("end") Date end){
        try {
            return ResponseEntity.ok(this.tradeManagement.getAllSaleByPeriod(start,end));
        }catch (Exception e){
            throw new SaleNotFoundException(String.format("Any sales registered between %s and %s : \n%s",start,end,e.getMessage()));
        }
    }

    @PutMapping(path = "sales/{id}")
    public void editSale(@RequestBody Sale sale, @PathVariable Long id){
        try {
            this.tradeManagement.updateSale(sale,id);
        }catch (Exception e){
            throw new SaleRegistrationFailedException(String.format("Update sale with id %d failed: \n%s",id,e.getMessage()));
        }
    }

    @DeleteMapping(path = "sales/{id}")
    public void deleteSale(@PathVariable Long id){
        try {
            this.tradeManagement.deleteSale(id);
        }catch (Exception e){
            throw new SaleNotFoundException(String.format("Delete sale with id %d failed: \n%s",id,e.getMessage()));
        }
    }
}
