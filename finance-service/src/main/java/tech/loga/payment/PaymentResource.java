package tech.loga.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentResource implements PaymentManagement {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    @Transactional
    public Payment registerPayment(Payment payment) {
        return this.paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        if(this.paymentRepository.findById(id).isPresent()){
            return this.paymentRepository.findById(id).get();
        }
        throw new RuntimeException(String.format("Payment with id:%d not found",id));
    }

    @Override
    public Payment getPaymentByReference(String reference) {
        if(this.paymentRepository.findByReference(reference).isPresent()){
            return this.paymentRepository.findByReference(reference).get();
        }
        throw new RuntimeException(String.format("Payment with reference:%s not found",reference));
    }

    @Override
    public List<Payment> getAllPayment() {
        return this.paymentRepository.findAll();
    }

    @Override
    public void updatePayment(Payment payment, Long id) {
        this.paymentRepository
                .findById(id)
                .ifPresentOrElse(up -> {
                    up.setAmount(payment.getAmount());
                    up.setMode(payment.getMode());
                    up.setClosedAt(payment.getClosedAt());
                    this.paymentRepository.saveAndFlush(up);
                },() -> {
                    throw new PaymentRegistrationFailedException(String.format("Update payment with id : %d failed",id));
                });
    }

    @Override
    public void deletePayment(Long id) {
        this.paymentRepository
                .findById(id)
                .ifPresentOrElse(payment -> {
                    this.paymentRepository.delete(payment);
                },() -> {
                    throw new PaymentNotFoundException(String.format("Delete payment with id : %d failed",id));
                });
    }
}
