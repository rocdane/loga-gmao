package tech.loga.customer;

import org.springframework.stereotype.Component;
import tech.loga.automobile.Automobile;
import tech.loga.client.Client;
import tech.loga.dossier.Dossier;

import java.util.Date;

@Component
public class CustomerBuilder {

    public String clean(String text){
        return text.replaceAll("\\s", "");
    }
    
    public Dossier build(Customer customer){
        StringBuilder reference = new StringBuilder();
        reference
                .append(clean(customer.name()).substring(0,6).toUpperCase())
                .append(clean(customer.number()).substring(0,6).toUpperCase())
                .append(clean(customer.vin().substring(10,16).toUpperCase()));

        Dossier dossier = new Dossier();
        dossier.setReference(reference.toString());
        dossier.setArchived(false);
        dossier.setClient(new Client(customer.name(), customer.type(), customer.legal(), customer.address(), customer.contact()));
        dossier.setAutomobile(new Automobile(customer.number(), customer.vin(), customer.make(), customer.model(), customer.trim(),customer.unit()));
        return dossier;
    }

    public Dossier build(Client client, Automobile automobile){
        StringBuilder reference = new StringBuilder();
        reference
                .append(clean(client.getName()).substring(0,6).toUpperCase())
                .append(clean(automobile.getNumber()).substring(0,6).toUpperCase())
                .append(clean(automobile.getVin().substring(10,16).toUpperCase()));

        Dossier dossier = new Dossier();
        dossier.setReference(reference.toString());
        dossier.setArchived(false);
        dossier.setOpenAt(new Date(System.currentTimeMillis()));
        dossier.setUpdatedAt(new Date(System.currentTimeMillis()));
        dossier.setClient(client);
        dossier.setAutomobile(automobile);
        return dossier;
    }
}
