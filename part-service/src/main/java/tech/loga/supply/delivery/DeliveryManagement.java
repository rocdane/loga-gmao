package tech.loga.supply.delivery;

import java.util.List;

public interface DeliveryManagement {
    Delivery registerDelivery(Delivery delivery);
    Delivery getDelivery(Long id);
    List<Delivery> getAllDelivery(Long orderId);
    void deleteDelivery(Long id);
}
