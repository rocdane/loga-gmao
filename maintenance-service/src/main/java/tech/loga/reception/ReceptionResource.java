package tech.loga.reception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ReceptionService implements IReceptionService {

    @Autowired
    private ReceptionRepository receptionRepository;

    @Override
    @Transactional
    public Reception create(Reception reception) {
        reception.setCreatedAt(new Date());
        if(receptionRepository.findByReference(reception.getReference())!=null)
            return null;
        else
            return receptionRepository.save(reception);
    }

    @Override
    public Reception read(Long id) {
        return receptionRepository.findById(id).get();
    }

    @Override
    public List<Reception> readAll() {
        return receptionRepository.findAll();
    }
}
