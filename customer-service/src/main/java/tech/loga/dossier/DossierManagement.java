package tech.loga.dossier;

import java.util.List;

public interface DossierManagement {
    Dossier createDossier(Dossier dossier);
    List<Dossier> getAllDossier();
    List<Dossier> getAllDossierByClientName(String name);
    List<Dossier> getAllDossierByAutomobileNumber(String number);
    Dossier getDossierById(Long id);
    Dossier getDossierByReference(String reference);
    Dossier getDossierByAutomobileNumber(String number);
    void editDossier(Dossier dossier, Long id);
    void archiveDossier(Long id);
    void deleteDossier(Long id);
}
