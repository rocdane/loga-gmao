package tech.loga.reception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.loga.vendor.ReferenceBuilder;

import java.util.Date;
import java.util.List;

@Service
public class ReceptionResource implements ReceptionManagement {

    private final ReceptionRepository receptionRepository;
    private final ReferenceBuilder referenceBuilder;

    @Autowired
    public ReceptionResource(ReceptionRepository receptionRepository,
                             ReferenceBuilder referenceBuilder) {
        this.receptionRepository = receptionRepository;
        this.referenceBuilder = referenceBuilder;
    }

    @Override
    @Transactional
    public Reception createReception(Reception reception) {
        reception.setCreatedAt(new Date(System.currentTimeMillis()));
        reception.setReference(referenceBuilder.build(reception.getCustomer()));
        return receptionRepository.save(reception);
    }

    @Override
    public Reception getReceptionById(Long id) {
        if(receptionRepository.findById(id).isPresent()){
            return receptionRepository.findById(id).get();
        }else{
            throw new RuntimeException(String.format("Reception with id : %d not found",id));
        }
    }

    @Override
    public Reception getReceptionByReference(String reference) {
        if(receptionRepository.findByReference(reference).isPresent()){
            return receptionRepository.findByReference(reference).get();
        }else{
            throw new RuntimeException("Reception registration failed");
        }
    }

    @Override
    public List<Reception> getAllReception() {
        return receptionRepository.findAll();
    }
}