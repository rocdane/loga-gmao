package tech.loga.payment;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("finance-service")
public class PaymentController {

    private PaymentManagement paymentManagement;

    @PostMapping(path = "payments", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Payment> registerPayment(@RequestBody Payment payment){
        try {
            return ResponseEntity.ok(this.paymentManagement.registerPayment(payment));
        }catch (Exception e){
            throw new PaymentRegistrationFailedException(String.format("Register payment failed : \n%s",e.getMessage()));
        }
    }

    @GetMapping(path = "payments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Payment> getPayment(@PathVariable Long id){
        try {
            return ResponseEntity.ok(this.paymentManagement.getPaymentById(id));
        }catch (Exception e){
            throw new PaymentNotFoundException(String.format("Register with id : %d not found : \n%s",id,e.getMessage()));
        }
    }

    @GetMapping(path = "payments/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Payment> getPayment(@PathVariable String reference){
        try {
            return ResponseEntity.ok(this.paymentManagement.getPaymentByReference(reference));
        }catch (Exception e){
            throw new PaymentNotFoundException(String.format("Register with reference : %s not found : \n%s",reference,e.getMessage()));
        }
    }

    @GetMapping(path = "payments", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Payment>> getPayments(){
        try {
            return ResponseEntity.ok(this.paymentManagement.getAllPayment());
        }catch (Exception e){
            throw new PaymentNotFoundException(String.format("Any register found : \n%s",e.getMessage()));
        }
    }

    @PutMapping(path = "payments/{id}")
    public void updatePayment(@RequestBody Payment payment, @PathVariable Long id){
        try {
            this.paymentManagement.updatePayment(payment, id);
        }catch (Exception e){
            throw new PaymentRegistrationFailedException(String.format("Update payment with id : %d failed : \n%s",id,e.getMessage()));
        }
    }

    @DeleteMapping(path = "payments/{id}")
    public void deletePayment(@PathVariable Long id){
        try {
            this.paymentManagement.deletePayment(id);
        }catch (Exception e){
            throw new PaymentNotFoundException(String.format("Delete payment with id : %d failed : \n%s",id,e.getMessage()));
        }
    }
}
