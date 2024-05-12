package tech.loga.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreResource implements StoreManagement {

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    @Autowired
    public StoreResource(ProductRepository productRepository,
                         StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public Product registerProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProduct(String designation) {
        return productRepository.findAllByDesignationContaining(designation);
    }

    @Override
    public Product getProduct(Long id) {
        if(productRepository.findById(id).isPresent())
            return productRepository.findById(id).get();
        throw new ProductNotFoundException(String.format("Product with id : %d not found.",id));
    }

    @Override
    public Product getProduct(String reference) {
        if(productRepository.findByReference(reference).isPresent()){
            return productRepository.findByReference(reference).get();
        }
        throw new ProductNotFoundException(String.format("Product with reference: %s not found",reference));
    }

    @Override
    public Stock getStock(Long id) {
        if(this.stockRepository.findByProductId(id).isPresent()){
            return this.stockRepository.findByProductId(id).get();
        }
        throw new ProductNotFoundException(String.format("Any Stock found for product with id:%d",id));
    }

    @Override
    public Stock getStock(String reference) {
        if(this.stockRepository.findByProductReference(reference).isPresent()){
            return this.stockRepository.findByProductReference(reference).get();
        }
        throw new ProductNotFoundException(String.format("Any Stock found for product with reference:%s",reference));
    }

    @Override
    public void increaseStock(Long id, Integer quantity) {
        this.stockRepository
                .findByProductId(id)
                .ifPresentOrElse(stock -> {
                    stock.increase(quantity);
                    this.stockRepository.saveAndFlush(stock);
                },() -> {
                    throw new RuntimeException(String.format("Failed to increase stock by %d",quantity));
                });
    }

    @Override
    public void decreaseStock(Long id, Integer quantity) {
        this.stockRepository
                .findByProductId(id)
                .ifPresentOrElse(stock -> {
                    stock.decrease(quantity);
                    this.stockRepository.saveAndFlush(stock);
                },() -> {
                    throw new RuntimeException(String.format("Failed to decrease stock by %d",quantity));
                });
    }

    @Override
    public void updateProduct(Product product, Long id) {
        productRepository
                .findById(id)
                .ifPresentOrElse(up -> {
                    up.setReference(product.getReference());
                    up.setCategory(product.getCategory());
                    up.setBrand(product.getBrand());
                    up.setDesignation(product.getDesignation());
                    up.setDescription(product.getDescription());
                    up.setPrice(product.getPrice());
                    up.setLimit(product.getLimit());
                    productRepository.saveAndFlush(up);
                },() -> {
                    throw new ProductRegistrationFailedException(String.format("Update product with id:%d failed",id));
                });
    }

    @Override
    public void deleteProduct(Long id) {
        this.productRepository
                .findById(id)
                .ifPresentOrElse(this.productRepository::delete,() -> {
                    throw new ProductNotFoundException(String.format("Delete product with id:%d failed",id));
                });
    }
}
