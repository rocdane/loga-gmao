package tech.loga.supply.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.loga.supply.Article;

import java.util.List;

@RestController
@RequestMapping("part-service")
public class OrderController {

    @Autowired
    private OrderManagement orderManagement;

    @PostMapping(path = "/orders/supplier/{supplierId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> registerOrder(@PathVariable Long supplierId, @RequestBody List<Article> articles){
        return ResponseEntity.ok(this.orderManagement.registerOrder(supplierId,articles));
    }

    @GetMapping(path = "/orders/supplier/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getOrders(@PathVariable Long id){
        return ResponseEntity.ok(this.orderManagement.getAllOrder(id));
    }

    @GetMapping(path = "/orders/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> readOrders(@PathVariable Long id){
        return ResponseEntity.ok(this.orderManagement.getOrder(id));
    }

    @DeleteMapping(path = "/orders/{id}")
    public void deleteOrder(@PathVariable Long id){
        this.orderManagement.deleteOrder(id);
    }
}
