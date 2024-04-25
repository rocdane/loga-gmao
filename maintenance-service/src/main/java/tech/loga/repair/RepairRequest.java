package tech.loga.repair;

import java.util.List;

public record RepairRequest(
        String customer,
        String employee,
        Integer mileage,
        String description,
        List<Task> tasks,
        List<Spare> spares
){}
