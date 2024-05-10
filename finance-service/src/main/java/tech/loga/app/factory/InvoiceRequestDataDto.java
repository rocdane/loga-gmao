package tech.loga.app.factory;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequestDataDto{
    private String ifu;
    private String aib;
    private String type;
    private List<ItemDto> items;
    private ClientDto client;
    private OperatorDto operator;
    private List<PaymentDto> payment;
    private String reference;
}
