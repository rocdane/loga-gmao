package tech.loga.trade;

import java.util.Date;
import java.util.List;

public interface TradeManagement {
    Sale registerSale(Sale sale);
    Sale getSale(Long id);
    Sale getSale(String reference);
    List<Sale> getAllSale();
    List<Sale> getAllSale(Date date);
    List<Sale> getAllSaleByPeriod(Date start, Date end);
    void updateSale(Sale sale, Long id);
    void deleteSale(Long id);
}
