package tech.loga.automobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutomobileResource implements AutomobileManagement {

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
        if(automobileRepository.findById(id).isPresent()){
            return automobileRepository.findById(id).get();
        }
        throw new RuntimeException(String.format("Not found with %d",id));
    }

    @Override
    public Automobile getAutomobileByNumber(String number) {
        if(automobileRepository.findByNumberIgnoreCase(number).isPresent()){
            return automobileRepository.findByNumberIgnoreCase(number).get();
        }
        throw new RuntimeException(String.format("Not found with %s",number));
    }

    @Override
    public Automobile getAutomobileByVin(String vin) {
        if(automobileRepository.findByVinIgnoreCase(vin).isPresent()){
            return automobileRepository.findByVinIgnoreCase(vin).get();
        }
        throw new RuntimeException(String.format("Not found with %s",vin));
    }

    @Override
    @Transactional
    public void editAutomobile(Automobile automobile, Long id) {
        automobileRepository
                .findById(id)
                .ifPresentOrElse(up -> {
                    up.setVin(automobile.getVin());
                    up.setNumber(automobile.getNumber());
                    up.setMake(automobile.getMake());
                    up.setModel(automobile.getModel());
                    up.setTrim(automobile.getTrim());
                    up.setUnit(automobile.getUnit());
                    automobileRepository.saveAndFlush(up);
                },() -> {
                    throw new RuntimeException(String.format("Not found with %d",id));
                });
    }

    @Override
    @Transactional
    public void deleteAutomobile(Long id) {
        automobileRepository
                .findById(id)
                .ifPresentOrElse(automobile -> {
                    automobileRepository.delete(automobile);
                },() -> {
                    throw new RuntimeException(String.format("Not found with %d",id));
                });
    }
}
