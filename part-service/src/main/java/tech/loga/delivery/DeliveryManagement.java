package tech.loga.delivery;

import java.util.List;

public interface DeliveryManagement {
    Delivery registerDelivery(Delivery delivery);
    Delivery getDelivery(Long id);
    List<Delivery> getAllDelivery();
    void editDelivery(Delivery delivery, Long id);
    void deleteDelivery(Long id);
}
