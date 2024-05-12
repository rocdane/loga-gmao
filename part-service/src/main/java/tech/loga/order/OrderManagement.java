package tech.loga.order;

import java.util.List;

public interface OrderManagement {
    Order registerOrder(Order order);
    Order getOrder(Long id);
    List<Order> getAllOrder();
    void editOrder(Order order, Long id);
    void deleteOrder(Long id);
}
