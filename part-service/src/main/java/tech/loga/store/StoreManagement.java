package tech.loga.store;

import java.util.List;

public interface StoreManagement {
    Product registerProduct(Product product);
    List<Product> getAllProduct();
    List<Product> getAllProduct(String reference);
    Product getProduct(Long id);
    Product getProduct(String reference);
    Stock getStock(Long id);
    Stock getStock(String reference);
    void increaseStock(Long id, Integer quantity);
    void decreaseStock(Long id, Integer quantity);
    void updateProduct(Product product, Long id);
    void deleteProduct(Long id);
}
