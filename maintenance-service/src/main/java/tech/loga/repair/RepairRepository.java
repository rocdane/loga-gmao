package tech.loga.repair;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<Repair,Long> {
    Repair findByReference(String reference);
    List<Repair> findByReferenceContaining(String reference);
    List<Repair> findAllByCreatedAtBetween(Date start, Date end);
}
