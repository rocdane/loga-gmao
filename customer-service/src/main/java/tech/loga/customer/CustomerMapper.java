package tech.loga.customer;

import org.springframework.stereotype.Service;
import tech.loga.dossier.Dossier;

import java.util.function.Function;

@Service
public class CustomerMapper implements Function<Dossier, Customer> {
    @Override
    public Customer apply(Dossier dossier) {
        return new Customer(
                dossier.getId(),
                dossier.getReference(),
                dossier.getClient().getName(),
                dossier.getClient().getType(),
                dossier.getClient().getLegal(),
                dossier.getClient().getAddress(),
                dossier.getClient().getContact(),
                dossier.getAutomobile().getNumber(),
                dossier.getAutomobile().getVin(),
                dossier.getAutomobile().getMake(),
                dossier.getAutomobile().getModel(),
                dossier.getAutomobile().getTrim(),
                dossier.getAutomobile().getUnit()
        );
    }
}
