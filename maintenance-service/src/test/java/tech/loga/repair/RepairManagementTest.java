package tech.loga.repair;

import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RepairManagementTest {

    @Autowired
    private RepairManagement repairManagement;

    private final RepairRequest REQUEST = new RepairRequest(
            "CUSTOMERDMLOSPDJKCNDFJKOPMLFDJKN",
            "EMPLOYEE",
            10000,
            "NEW REPAIR",
            new ArrayList<>(),
            new ArrayList<>()
    );

    @Test
    void createRepair() {
        Repair repair = new Repair();
        repair.setCustomer(REQUEST.customer());
        repair.setEmployee(REQUEST.employee());
        repair.setMileage(REQUEST.mileage());
        repair.setDescription(REQUEST.description());
        repair.getSpares().add(new Spare(null,"SPARE",10000,1,10000));
        repair.getTasks().add(new Task(null,"TASK",10000,2));
        repairManagement.createRepair(repair);
        Assertions.assertNotNull(repair.getId());
    }

    @Test
    void getAllRepair() {
        Assertions.assertNotNull(repairManagement.getAllRepair().get(0));
    }

    @Test
    void getRepairById() {
        Assertions.assertNotNull(repairManagement.getRepairById(1L));
    }

    @Test
    void orderRepair() {
        repairManagement.orderRepair(1L);
        Assertions.assertTrue(repairManagement.getRepairById(1L).getApproved());
    }
}