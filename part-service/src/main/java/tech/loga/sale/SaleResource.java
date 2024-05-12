package tech.loga.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SaleResource implements SaleManagement {

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public Sale registerSale(Sale sale) {
        return this.saleRepository.save(sale);
    }

    @Override
    public Sale getSale(Long id) {
        if(this.saleRepository.findById(id).isPresent()){
            return this.saleRepository.findById(id).get();
        }
        throw new SaleNotFoundException(String.format("Sale with id:%d not found",id));
    }

    @Override
    public Sale getSale(String reference) {
        if(this.saleRepository.findByReference(reference).isPresent()){
            return this.saleRepository.findByReference(reference).get();
        }
        throw new SaleNotFoundException(String.format("Sale with reference:%s not found",reference));
    }

    @Override
    public List<Sale> getAllSale() {
        return this.saleRepository.findAll();
    }

    @Override
    public List<Sale> getAllSale(Date date) {
        return this.saleRepository.findAllByCreatedAt(date);
    }

    @Override
    public List<Sale> getAllSaleByPeriod(Date start, Date end) {
        return this.saleRepository.findAllByCreatedAtBetween(start,end);
    }

    @Override
    public void updateSale(Sale sale, Long id) {
        this.saleRepository
                .findById(id)
                .ifPresentOrElse(up -> {
                    up.setCustomer(sale.getCustomer());
                    up.setUpdatedAt(new Date(System.currentTimeMillis()));
                    this.saleRepository.save(up);
                },() -> {
                    throw new SaleRegistrationFailedException(String.format("Update Sale with id: %d failed",id));
                });
    }

    @Override
    public void deleteSale(Long id) {
        this.saleRepository
                .findById(id)
                .ifPresentOrElse(this.saleRepository::delete,() -> {
                    throw new SaleRegistrationFailedException(String.format("Delete Sale with id: %d failed",id));
                });
    }
}
