package tech.loga.customer;

import tech.loga.automobile.Automobile;
import tech.loga.client.Client;
import tech.loga.dossier.Dossier;

public interface RegistrateCustomer {
    Dossier createCustomer(Dossier dossier);
    Dossier createCustomer(Client client, Automobile automobile);
}
