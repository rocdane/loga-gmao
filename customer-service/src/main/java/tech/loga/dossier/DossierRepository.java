package tech.loga.dossier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DossierRepository extends JpaRepository<Dossier,Long> {

    Optional<Dossier> findByReferenceIgnoreCase(String reference);
    Optional<Dossier> findDossierByAutomobileNumberIgnoreCase(String number);
    List<Dossier> findAllByAutomobileNumberContainingIgnoreCase(String number);
    List<Dossier> findAllByClientNameContainingIgnoreCase(String name);
}

