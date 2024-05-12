package tech.loga.payment;

import java.util.List;

public interface PaymentManagement {
    Payment registerPayment(Payment payment);
    Payment getPaymentById(Long id);
    Payment getPaymentByReference(String reference);
    List<Payment> getAllPayment();
    void updatePayment(Payment payment,Long id);
    void deletePayment(Long id);
}
