package tech.loga.reception;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceptionRepository extends JpaRepository<Reception,Long> {
    Optional<Reception> findByReference(String reference);
}
