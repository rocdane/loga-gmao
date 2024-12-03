package tech.loga.supply.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.loga.supplier.SupplierManagement;
import tech.loga.supply.Article;

import java.util.List;

@Service
public class OrderResource implements OrderManagement{

    private final OrderRepository orderRepository;
    private final SupplierManagement supplierManagement;

    @Autowired
    public OrderResource(OrderRepository orderRepository,
                         SupplierManagement supplierManagement) {
        this.orderRepository = orderRepository;
        this.supplierManagement = supplierManagement;
    }


    @Override
    public Order registerOrder(Long supplierId, List<Article> articles) {
        Order order = new Order();
        order.setSupplier(supplierManagement.getSupplier(supplierId));
        order.setArticles(articles);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrder(Long supplierId) {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrder(Long id) {
        if(orderRepository.findById(id).isPresent()){
            return orderRepository.findById(id).get();
        }else{
            throw new OrderNotFoundException(String.format("Order with id:%d not found",id));
        }
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
