package tech.loga.automobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutomobileResource implements AutomobileManagement{
    @Autowired
    private AutomobileRepository automobileRepository;

    @Override
    @Transactional
    public Automobile createAutomobile(Automobile automobile) {
        return automobileRepository.save(automobile);
    }

    @Override
    public List<Automobile> getAllAutomobile() {
        return automobileRepository.findAll();
    }

    @Override
    public Automobile getAutomobileById(Long id) {
        return automobileRepository.findById(id).get();
    }

    @Override
    public Automobile getAutomobileByNumber(String number) {
        return automobileRepository.findByNumberIgnoreCase(number).get();
    }

    @Override
    public Automobile getAutomobileByVin(String vin) {
        return automobileRepository.findByVinIgnoreCase(vin).get();
    }

    @Override
    @Transactional
    public void editAutomobile(Automobile automobile, Long id) {
        automobileRepository
                .findById(id) // returns Optional<User>
                .ifPresent(up -> {
                    up.setVin(automobile.getVin());
                    up.setNumber(automobile.getNumber());
                    up.setMake(automobile.getMake());
                    up.setModel(automobile.getModel());
                    up.setTrim(automobile.getTrim());
                    up.setUnit(automobile.getUnit());
                    automobileRepository.saveAndFlush(up);
                });
    }

    @Override
    @Transactional
    public void deleteAutomobile(Long automobile) {
        automobileRepository.deleteById(automobile);
    }
}
