package tech.loga.supply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnisherRepository extends JpaRepository<Furnisher,Long> {
}
