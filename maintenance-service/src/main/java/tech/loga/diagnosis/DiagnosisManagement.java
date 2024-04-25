package tech.loga.diagnosis;

import java.util.List;

public interface DiagnosisManagement {
    Diagnosis createDiagnosis(Diagnosis diagnosis);
    Diagnosis getDiagnosisById(Long id);
    Diagnosis getDiagnosisByReference(String reference);
    List<Diagnosis> getAllDiagnosis();
}
