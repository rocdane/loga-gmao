package tech.loga.automobile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AutomobileRepository extends JpaRepository<Automobile,Long> {
    Optional<Automobile> findByNumberIgnoreCase(String number);
    Optional<Automobile> findByVinIgnoreCase(String vin);
}
