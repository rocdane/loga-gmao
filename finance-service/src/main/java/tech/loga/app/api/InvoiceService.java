package tech.loga.app.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tech.loga.app.factory.InvoiceDetailsDto;
import tech.loga.app.factory.InvoiceRequestDataDto;
import tech.loga.app.factory.InvoiceResponseDataDto;
import tech.loga.app.factory.SecurityElementsDto;

@FeignClient("gateway-server")
public interface InvoiceService {

    @PostMapping(path = "/invoice-service/invoicing", produces = MediaType.APPLICATION_JSON_VALUE)
    InvoiceResponseDataDto invoicing(@RequestBody InvoiceRequestDataDto invoice);

    @GetMapping(path = "/invoice-service/details/{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
    InvoiceDetailsDto details(@PathVariable String uid);

    @PutMapping(path = "/invoice-service/confirmation/{uid}/{action}", produces = MediaType.APPLICATION_JSON_VALUE)
    SecurityElementsDto confirmation(@PathVariable String uid, @PathVariable String action);
}
