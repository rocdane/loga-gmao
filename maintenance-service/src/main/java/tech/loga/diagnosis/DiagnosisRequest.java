package tech.loga.diagnosis;

import java.util.List;

public record DiagnosisRequest(
        String customer,
        String employee,
        Integer mileage,
        String description,
        List<Factor> factors
){}
