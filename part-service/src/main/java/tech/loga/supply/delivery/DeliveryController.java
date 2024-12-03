package tech.loga.supply.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("part-service")
public class DeliveryController {

    @Autowired
    private DeliveryManagement deliveryManagement;

    @PostMapping(path = "/delivery",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Delivery> registerDelivery(@RequestBody Delivery delivery){
        return ResponseEntity.ok(this.deliveryManagement.registerDelivery(delivery));
    }

    @GetMapping(path = "/delivery/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Delivery> getDelivery(@PathVariable Long id){
        return ResponseEntity.ok(this.deliveryManagement.getDelivery(id));
    }

    @GetMapping(path = "/delivery/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Delivery>> getDeliveries(@PathVariable Long id){
        return ResponseEntity.ok(this.deliveryManagement.getAllDelivery(id));
    }

    @DeleteMapping(path = "/delivery/{id}")
    public void deleteDelivery(@PathVariable Long id){
        this.deliveryManagement.deleteDelivery(id);
    }
}
