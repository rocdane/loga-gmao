package tech.loga.dossier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DossierRepository extends JpaRepository<Dossier,Long> {

    Optional<Dossier> findByReference(String reference);
    Optional<Dossier> findDossierByAutomobileNumber(String number);
    List<Dossier> findAllByAutomobileNumberContaining(String number);
    List<Dossier> findAllByClientNameContaining(String name);
}

