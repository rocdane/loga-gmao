package tech.loga.automobile;

import java.util.List;

public interface AutomobileManagement {
    Automobile createAutomobile(Automobile automobile);
    List<Automobile> getAllAutomobile();
    Automobile getAutomobileById(Long id);
    Automobile getAutomobileByNumber(String number);
    Automobile getAutomobileByVin(String vin);
    void editAutomobile(Automobile automobile, Long id);
    void deleteAutomobile(Long id);
}
