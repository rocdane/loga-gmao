package tech.loga.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderResource implements OrderManagement{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order registerOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrder() {
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
    public void editOrder(Order order, Long id) {
        orderRepository
                .findById(id)
                .ifPresent(up -> {
                    up.setQuantity(order.getQuantity());
                    orderRepository.saveAndFlush(up);
                });
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
