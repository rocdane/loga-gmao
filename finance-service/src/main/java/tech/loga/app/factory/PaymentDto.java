package tech.loga.app.factory;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    String paymentType;
    Integer amount;
}
