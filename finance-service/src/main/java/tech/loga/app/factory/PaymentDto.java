package tech.loga.app.factory;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    String paymentType;
    Integer amount;
}
