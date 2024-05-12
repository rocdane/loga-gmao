package tech.loga.trade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
    Optional<Sale> findByReference(String reference);
    List<Sale> findAllByCreatedAt(Date date);
    List<Sale> findAllByCreatedAtBetween(Date start, Date end);
}
