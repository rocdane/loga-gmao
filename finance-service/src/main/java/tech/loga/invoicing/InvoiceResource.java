package tech.loga.invoicing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.loga.app.api.InvoiceService;
import tech.loga.app.factory.*;

@Service
public class InvoiceResource implements InvoiceManagement {

    private final InvoiceService emecef;
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceResource(InvoiceRepository invoiceRepository, InvoiceService emecef) {
        this.invoiceRepository = invoiceRepository;
        this.emecef = emecef;
    }

    @Override
    public void invoicing(InvoiceRequest invoiceRequest) {

        InvoiceRequestDataDto invoiceRequestDataDto = new InvoiceRequestDataDto();
        invoiceRequestDataDto.setClient(new ClientDto());
        invoiceRequestDataDto.setReference("");
        invoiceRequestDataDto.setType("FV");
        invoiceRequestDataDto.setOperator(new OperatorDto(null,"SFE_MECEF"));

        InvoiceResponseDataDto invoiceResponseDataDto = this.emecef.invoicing(invoiceRequestDataDto);
        Invoice invoice = new Invoice();
        invoice.setReference(invoiceResponseDataDto.getUid());
        this.invoiceRepository.save(invoice);
    }

    @Override
    public void process(String uid, String action){
        InvoiceDetailsDto invoiceDetailsDto = this.emecef.details(uid);

        if(invoiceDetailsDto!=null){
            SecurityElementsDto securityElementsDto = this.emecef.confirmation(uid,action);
        }
    }
}
