package tech.loga.supply;

import java.util.List;

public interface ISupplyService {
    Furnisher registrateFurnisher(Furnisher furnisher);
    Furnisher readFurnisher(Long id);
    List<Furnisher> listFurnisher();
    void editFurnisher(Furnisher furnisher, Long id);
    void deleteFurnisher(Long id);
    Order registrateOrder(Order order);
    List<Order> listOrder();
    List<Order> listOrder(String reference);
    Order readOrder(Long id);
    void editOrder(Order order, Long id);
    void editOrder(Article article, Long id);
    void editOrder(Delivery delivery, Long id);
    void deleteOrder(Long id);
    Delivery registrateDelivery(Delivery delivery);
    Delivery readDelivery(Long id);
    List<Delivery> listDelivery();
    void deleteDelivery(Long id);
}
