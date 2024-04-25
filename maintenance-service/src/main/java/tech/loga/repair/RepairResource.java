package tech.loga.repair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.loga.vendor.ReferenceBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RepairResource implements RepairManagement {

    private final ReferenceBuilder referenceBuilder;
    private final RepairRepository repairRepository;
    private final TaskRepository taskRepository;
    private final SpareRepository spareRepository;

    @Autowired
    public RepairResource(ReferenceBuilder referenceBuilder,
                          RepairRepository repairRepository,
                          TaskRepository taskRepository,
                          SpareRepository spareRepository){
        this.referenceBuilder = referenceBuilder;
        this.repairRepository = repairRepository;
        this.taskRepository = taskRepository;
        this.spareRepository = spareRepository;
    }

    @Override
    @Transactional
    public Repair createRepair(Repair repair) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        repair.setCreatedAt(new Date());
        repair.setReference(referenceBuilder.build(repair.getCustomer()));
        return repairRepository.save(repair);
    }

    @Override
    public List<Repair> getAllRepair() {
        return repairRepository.findAll();
    }

    @Override
    public List<Repair> getAllRepairByPeriod(Date debut, Date fin) {
        return repairRepository.findAllByCreatedAtBetween(debut,fin);
    }

    @Override
    public Repair getRepairByReference(String reference) {
        if(repairRepository.findByReferenceIgnoreCase(reference).isPresent()){
            return repairRepository.findByReferenceIgnoreCase(reference).get();
        }else {
            throw new RuntimeException(String.format("Repair with reference: %s not found",reference));
        }
    }

    @Override
    public Repair getRepairById(Long id) {
        if(repairRepository.findById(id).isPresent()){
            return repairRepository.findById(id).get();
        }else{
            throw new RuntimeException(String.format("Repair with id : %s not found",id));
        }
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
