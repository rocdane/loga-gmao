package tech.loga.dossier;

import tech.loga.client.Client;
import tech.loga.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DossierResource implements DossierManagement{

    @Autowired
    private DossierRepository dossierRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Transactional
    public Dossier createDossier(Dossier dossier) {
        Client client = null;

        if(clientRepository.findByNameIgnoreCase(dossier.getClient().getName()).isPresent()) {
            client = clientRepository.findByNameIgnoreCase(dossier.getClient().getName()).get();
        }

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
            return null;
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
        return dossierRepository.findById(id).isPresent() ? dossierRepository.findById(id).get() : null;
    }

    @Override
    public Dossier getDossierByReference(String reference){
        return dossierRepository.findByReferenceIgnoreCase(reference).get();
    }

    @Override
    public Dossier getDossierByAutomobileNumber(String number){
        return dossierRepository.findDossierByAutomobileNumberIgnoreCase(number).get();
    }

    @Override
    @Transactional
    public void editDossier(Dossier dossier, Long id){
        dossierRepository
                .findById(id)
                .ifPresent(up -> {
                    up.setReference(dossier.getReference());
                    up.setUpdatedAt(new Date());
                    dossierRepository.saveAndFlush(up);
                });
    }

    @Override
    public void deleteDossier(Long dossier) {
        dossierRepository.deleteById(dossier);
    }
}
