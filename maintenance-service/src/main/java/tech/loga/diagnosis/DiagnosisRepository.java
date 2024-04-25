package tech.loga.diagnosis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis,Long> {
    Optional<Diagnosis> findByReference(String reference);
}
