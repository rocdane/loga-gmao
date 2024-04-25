package tech.loga.diagnosis;

import java.util.List;

public interface IDiagnosisService {

    Diagnosis create(Diagnosis diagnosis);

    Diagnosis read(Long id);

    List<Diagnosis> readAll();
}
