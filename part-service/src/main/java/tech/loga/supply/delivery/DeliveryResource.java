package tech.loga.supply.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryResource implements DeliveryManagement {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public Delivery registerDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public Delivery getDelivery(Long id) {
        if(deliveryRepository.findById(id).isPresent())
            return deliveryRepository.findById(id).get();
        return null;
    }

    @Override
    public List<Delivery> getAllDelivery(Long orderId) {
        return deliveryRepository.findAll();
    }

    @Override
    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }
}
