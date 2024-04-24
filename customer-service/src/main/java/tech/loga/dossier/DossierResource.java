package tech.loga.dossier;

import tech.loga.client.Client;
import tech.loga.client.ClientManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DossierResource implements DossierManagement {

    @Autowired
    private DossierRepository dossierRepository;

    @Autowired
    private ClientManagement clientManagement;

    @Override
    @Transactional
    public Dossier createDossier(Dossier dossier) {
        Client client = clientManagement.getClientByName(dossier.getClient().getName());

        if (client!=null) {
            dossier.setClient(client);
        }

        Boolean notExistAutomobile = dossierRepository.findDossierByAutomobileNumberIgnoreCase(dossier.getAutomobile().getNumber()).isEmpty();
        Boolean notExistReference = dossierRepository.findByReferenceIgnoreCase(dossier.getReference()).isEmpty();

        if(notExistAutomobile && notExistReference){
            dossier.setOpenAt(new Date(System.currentTimeMillis()));
            dossier.setUpdatedAt(new Date(System.currentTimeMillis()));
            return dossierRepository.save(dossier);
        }else{
            throw new RuntimeException("Customer registration failed");
        }
    }

    @Override
    public List<Dossier> getAllDossier() {
        return dossierRepository.findAll();
    }

    @Override
    public List<Dossier> getAllDossierByClientName(String name){
        return dossierRepository.findAllByClientNameContainingIgnoreCase(name);
    }

    @Override
    public List<Dossier> getAllDossierByAutomobileNumber(String number) {
        return dossierRepository.findAllByAutomobileNumberContainingIgnoreCase(number);
    }

    @Override
    public Dossier getDossierById(Long id) {
        if(dossierRepository.findById(id).isPresent()){
            return dossierRepository.findById(id).get();
        }
        throw new RuntimeException(String.format("Dossier with id : %d not exists.",id));
    }

    @Override
    public Dossier getDossierByReference(String reference){
        if(dossierRepository.findByReferenceIgnoreCase(reference).isPresent()){
            return dossierRepository.findByReferenceIgnoreCase(reference).get();
        }
        throw new RuntimeException(String.format("Dossier with reference : %s not exists.",reference));
    }

    @Override
    public Dossier getDossierByAutomobileNumber(String number){
        if(dossierRepository.findDossierByAutomobileNumberIgnoreCase(number).isPresent()){
            return dossierRepository.findDossierByAutomobileNumberIgnoreCase(number).get();
        }
        throw new RuntimeException(String.format("Dossier with number : %s not exists.",number));
    }

    @Override
    @Transactional
    public void editDossier(Dossier dossier, Long id){
        dossierRepository
                .findById(id)
                .ifPresentOrElse(up -> {
                    up.setReference(dossier.getReference());
                    up.setUpdatedAt(new Date());
                    dossierRepository.saveAndFlush(up);
                },() -> {
                    throw new RuntimeException(String.format("Fail to update dossier with id : %d.",id));
                });
    }

    @Override
    public void archiveDossier(Long id) {
        dossierRepository
                .findById(id)
                .ifPresentOrElse(dossier -> {
                    dossier.setArchived(true);
                    dossierRepository.saveAndFlush(dossier);
                }, () -> {
                    throw new RuntimeException(String.format("Fail to archive dossier with id : %d.",id));
                });
    }

    @Override
    public void deleteDossier(Long id) {
        dossierRepository
                .findById(id)
                .ifPresentOrElse(dossier -> {
                    dossierRepository.delete(dossier);
                },() -> {
                    throw new RuntimeException(String.format("Fail to delete dossier with id:%d",id));
                });
    }
}
