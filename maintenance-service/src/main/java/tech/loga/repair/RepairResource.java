package tech.loga.repair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RepairManagement implements RepairManagement {

    private final RepairRepository repairRepository;
    private final TaskRepository taskRepository;
    private final SpareRepository spareRepository;

    @Autowired
    public RepairService(RepairRepository repairRepository, TaskRepository taskRepository, SpareRepository spareRepository){
        this.repairRepository = repairRepository;
        this.taskRepository = taskRepository;
        this.spareRepository = spareRepository;
    }

    @Override
    @Transactional
    public Repair createRepair(Repair repair) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        repair.setCreatedAt(new Date());
        repair.setReference(sdf.format(repair.getCreatedAt())+" - "+repair.getDossier());
        return repairRepository.save(repair);
    }

    @Override
    public List<Repair> listRepair() {
        return repairRepository.findAll();
    }

    @Override
    public List<Repair> listRepair(Date debut, Date fin) {
        return repairRepository.findAllByCreatedAtBetween(debut,fin);
    }

    @Override
    public Repair findRepair(String reference) {
        return repairRepository.findByReference(reference);
    }

    @Override
    public Repair findRepair(Long id) {
        return repairRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void editRepair(Repair repair, Long id) {
        repairRepository
                .findById(id)
                .ifPresent(up -> {
                    up.setReference(repair.getReference());
                    up.setDescription(repair.getDescription());
                    up.setMileage(repair.getMileage());
                    up.setApproved(repair.getApproved());
                    repairRepository.saveAndFlush(up);
                });
    }

    @Override
    public void orderRepair(Long id) {
        repairRepository
                .findById(id)
                .ifPresent(up -> {
                    up.setApproved(true);
                    repairRepository.saveAndFlush(up);
                });
    }

    @Override
    public void editTask(Task task, Long id) {
        taskRepository
                .findById(id)
                .ifPresent(up -> {
                    up.setDescription(task.getDescription());
                    up.setCost(task.getCost());
                    up.setDuration(task.getDuration());
                    taskRepository.saveAndFlush(up);
                });
    }

    @Override
    public void editSpare(Spare spare, Long id) {
        spareRepository
                .findById(id)
                .ifPresent(up -> {
                    up.setDesignation(spare.getDesignation());
                    up.setAmount(spare.getAmount());
                    up.setQuantity(spare.getQuantity());
                    up.setPrice(spare.getPrice());
                    spareRepository.saveAndFlush(up);
                });
    }

    @Override
    public void deleteRepair(Long repair) {
        repairRepository.deleteById(repair);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void deleteSpare(Long id) {
        spareRepository.deleteById(id);
    }
}
