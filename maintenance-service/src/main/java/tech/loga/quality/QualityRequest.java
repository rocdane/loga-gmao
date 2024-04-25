package tech.loga.quality;

import java.util.List;

public record QualityRequest(
        String customer,
        String employee,
        Integer mileage,
        String description,
        List<Control> controls
){}
