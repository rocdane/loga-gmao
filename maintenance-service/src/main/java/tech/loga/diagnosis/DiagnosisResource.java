package tech.loga.diagnosis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.loga.vendor.ReferenceBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DiagnosisResource implements DiagnosisManagement {

    private final DiagnosisRepository diagnosisRepository;
    private final ReferenceBuilder referenceBuilder;

    @Autowired
    public DiagnosisResource(DiagnosisRepository diagnosisRepository,
                             ReferenceBuilder referenceBuilder) {
        this.diagnosisRepository = diagnosisRepository;
        this.referenceBuilder = referenceBuilder;
    }

    @Override
    @Transactional
    public Diagnosis createDiagnosis(Diagnosis diagnosis) {
        diagnosis.setCreatedAt(new Date(System.currentTimeMillis()));
        diagnosis.setReference(referenceBuilder.build(diagnosis.getCustomer()));
        return diagnosisRepository.save(diagnosis);
    }

    @Override
    public Diagnosis getDiagnosisById(Long id) {
        if(diagnosisRepository.findById(id).isPresent()){
            return diagnosisRepository.findById(id).get();
        }else{
            throw new RuntimeException(String.format("Diagnosis with id : %d not found.",id));
        }
    }

    @Override
    public Diagnosis getDiagnosisByReference(String reference) {
        if(diagnosisRepository.findByReference(reference).isPresent()){
            return diagnosisRepository.findByReference(reference).get();
        }else{
            throw new RuntimeException(String.format("Diagnosis with id : %s not found.",reference));
        }
    }

    @Override
    public List<Diagnosis> getAllDiagnosis() {
        return diagnosisRepository.findAll();
    }
}
