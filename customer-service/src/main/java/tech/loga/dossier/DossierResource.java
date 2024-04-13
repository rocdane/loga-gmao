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

        if(clientRepository.findByName(dossier.getClient().getName()).isPresent()) {
            client = clientRepository.findByName(dossier.getClient().getName()).get();
        }

        if (client!=null) {
            dossier.setClient(client);
        }

        Boolean notExistAutomobile = dossierRepository.findDossierByAutomobileNumber(dossier.getAutomobile().getNumber()).isEmpty();
        Boolean notExistReference = dossierRepository.findByReference(dossier.getReference()).isEmpty();

        if(notExistAutomobile && notExistReference){
            dossier.setOpenAt(new Date());
            dossier.setUpdatedAt(new Date());
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
        return dossierRepository.findAllByClientNameContaining(name);
    }

    @Override
    public List<Dossier> getAllDossierByAutomobileNumber(String number) {
        return dossierRepository.findAllByAutomobileNumberContaining(number);
    }

    @Override
    public Dossier getDossierById(Long id) {
        return dossierRepository.findById(id).isPresent() ? dossierRepository.findById(id).get() : null;
    }

    @Override
    public Dossier getDossierByReference(String reference){
        return dossierRepository.findByReference(reference).get();
    }

    @Override
    public Dossier getDossierByAutomobileNumber(String number){
        return dossierRepository.findDossierByAutomobileNumber(number).get();
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
