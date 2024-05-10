package tech.loga.invoicing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("finance-service")
public class InvoiceController {

    @Autowired
    private InvoiceManagement invoiceManagement;

    @PostMapping(path = "/invoicing/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void invoicing(@RequestBody InvoiceRequest invoiceRequest){
        try {
            invoiceManagement.invoicing(invoiceRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(path = "/invoicing/{uid}/{action}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void process(@PathVariable String uid, @PathVariable String action){
        try {
            invoiceManagement.process(uid,action);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
