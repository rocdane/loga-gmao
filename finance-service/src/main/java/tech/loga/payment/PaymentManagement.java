package tech.loga.payment;

import java.util.List;

public interface PaymentManagement {
    Payment create(Payment payment);
    List<Payment> list();
}
