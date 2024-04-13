package tech.loga.automobile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AutomobileRepository extends JpaRepository<Automobile,Long> {
    List<Automobile> findAllByNumberContaining(String immatriculation);
    Optional<Automobile> findByNumber(String immatriculation);
    Optional<Automobile> findByVin(String vin);
}
