package tech.loga.repair;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.loga.api.ReportService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/maintenance-service")
public class RepairController {

    private final RepairManagement repairManagement;
    private final ReportService reportService;

    @Autowired
    public RepairController(RepairManagement repairManagement,
                            ReportService reportService) {
        this.repairManagement = repairManagement;
        this.reportService = reportService;
    }

    @PostMapping(path = "/repairs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Repair> create(@RequestBody RepairRequest request){
        try {
            Repair repair =
                    repairManagement.createRepair(new Repair(
                            request.employee(),
                            request.customer(),
                            request.mileage(),
                            request.description(),
                            request.tasks(),
                            request.spares()
                    ));

            return ResponseEntity.ok(repair);
        }catch (Exception e){
            throw new RepairRegistrationFailedException("Repair registration failed : \n"+e.getMessage());
        }
    }

    @GetMapping(path = "/repairs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Repair> read(@PathVariable Long id){
        try {
            Repair repair = repairManagement.getRepairById(id);
            return ResponseEntity.ok(repair);
        }catch (Exception e){
            throw new RepairNotFoundException(String.format("Repair with id : %d not found",id));
        }
    }

    @GetMapping(path = "/repairs/reference/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Repair> read(@PathVariable("reference") String reference){
        try {
            Repair repair = repairManagement.getRepairByReference(reference);
            return ResponseEntity.ok(repair);
        }catch (Exception e){
            throw new RepairNotFoundException(String.format("Repair with reference : %s not found",reference));
        }
    }

    @GetMapping(path = "/repairs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Repair>> read(){
        try {
            List<Repair> repairs = repairManagement.getAllRepair();
            return ResponseEntity.ok(repairs);
        }catch (Exception e){
            throw new RepairNotFoundException("Any repair found : \n"+e.getMessage());
        }
    }

    @PutMapping(path = "/repairs/{id}")
    public void edit(@RequestBody Repair repair, @PathVariable Long id){
        try {
            repairManagement.editRepair(repair,id);
        }catch (Exception e){
            throw new RepairRegistrationFailedException(String.format("Repair with id : %d registration failed: \n%s",id,e.getMessage()));
        }
    }

    @PutMapping(path = "/repairs/order/{id}")
    public void order(@PathVariable Long id){
        try {
            repairManagement.orderRepair(id);
        }catch (Exception e){
            throw new RepairRegistrationFailedException(String.format("Repair with id : %d registration failed: \n%s",id,e.getMessage()));
        }
    }

    @DeleteMapping(path = "/repairs/{id}")
    public void delete(@PathVariable Long id){
        try {
            repairManagement.deleteRepair(id);
        }catch (Exception e){
            throw new RepairNotFoundException(String.format("Repair with id : %d not found",id));
        }
    }

    @PutMapping(path = "/repairs/task/{id}")
    public void editTask(@RequestBody Task task, @PathVariable Long id){
        try {
            repairManagement.editTask(task,id);
        }catch (Exception e){
            throw new RepairRegistrationFailedException(String.format("Task with id : %d registration failed: \n%s",id,e.getMessage()));
        }
    }

    @DeleteMapping(path = "/repairs/task/{id}")
    public void deleteTask(@PathVariable Long id){
        try {
            repairManagement.deleteTask(id);
        }catch (Exception e){
            throw new RepairNotFoundException(String.format("Task with id : %d not found: \n%s",id,e.getMessage()));
        }
    }

    @PutMapping(path = "/repairs/spare/{id}")
    public void editSpare(@RequestBody Spare spare, @PathVariable Long id){
        try {
            repairManagement.editSpare(spare,id);
        }catch (Exception e){
            throw new RepairRegistrationFailedException(String.format("Spare with id : %d registration failed: \n%s",id,e.getMessage()));
        }
    }

    @DeleteMapping(path = "/repairs/spare/{id}")
    public void deleteSpare(@PathVariable Long id){
        try {
            repairManagement.deleteSpare(id);
        }catch (Exception e){
            throw new RepairNotFoundException(String.format("Spare with id : %d not found: \n%s",id,e.getMessage()));
        }
    }

    @GetMapping(path = "/repairs/report/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(HttpServletResponse response, @PathVariable Long id) {
        try {
            Repair repair = repairManagement.getRepairById(id);
            reportService.report(repair,"repair",response);
        }catch (Exception e){
            throw new RepairNotFoundException(String.format("Repair with id : %d reporting failed : \n%s",id, e.getMessage()));
        }
    }
}
