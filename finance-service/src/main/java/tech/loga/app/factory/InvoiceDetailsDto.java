package tech.loga.app.factory;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailsDto{
    private String ifu;
    private String aib;
    private String type;
    private List<ItemDto> items;
    private ClientDto client;
    private OperatorDto operator;
    private List<PaymentDto> payment;
    private String reference;
    private String errorCode;
    private String errorDesc;
    private String paymentUrl;
}
