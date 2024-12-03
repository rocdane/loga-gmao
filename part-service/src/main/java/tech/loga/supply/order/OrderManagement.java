package tech.loga.supply.order;

import tech.loga.supply.Article;

import java.util.List;

public interface OrderManagement {
    Order registerOrder(Long supplierId, List<Article> articles);
    Order getOrder(Long id);
    List<Order> getAllOrder(Long supplierId);
    void deleteOrder(Long id);
}
