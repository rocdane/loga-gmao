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
    public Payment create(Payment bill) {
        return paymentRepository.save(bill);
    }

    @Override
    public List<Payment> list() {
        return paymentRepository.findAll();
    }
}
