package tech.loga.repair;

import java.util.Date;
import java.util.List;

public interface RepairManagement {
    Repair createRepair(Repair repair);
    List<Repair> getAllRepair();
    List<Repair> getAllRepairByPeriod(Date debut, Date fin);
    Repair getRepairByReference(String reference);
    Repair getRepairById(Long id);
    void editRepair(Repair repair, Long id);
    void orderRepair(Long id);
    void editTask(Task task, Long id);
    void editSpare(Spare spare, Long id);
    void deleteRepair(Long id);
    void deleteTask(Long id);
    void deleteSpare(Long id);
}
