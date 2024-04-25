package tech.loga.reception;

import java.util.List;

public record ReceptionRequest(
        String customer,
        String employee,
        Integer mileage,
        String description,
        List<Notice> notices
){}
