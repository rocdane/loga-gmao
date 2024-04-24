package tech.loga.customer;

public record Customer(
        Long id,
        String reference,
        String name,
        String type,
        String legal,
        String address,
        String contact,
        String number,
        String vin,
        String make,
        String model,
        Integer trim,
        String unit
){}
