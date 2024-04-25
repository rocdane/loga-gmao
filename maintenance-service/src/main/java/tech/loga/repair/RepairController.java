package tech.loga.repair;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/maintenance-service")
public class RepairController {

    @Autowired
    private RepairManagement repairReparation;

    //private ReportService reportService;

    @PostMapping(path = "/repairs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Repair> create(@RequestBody RepairRequest request){
        Repair repair =
                repairReparation.createRepair(new Repair(
                        request.employee(),
                        request.customer(),
                        request.mileage(),
                        request.description(),
                        request.tasks(),
                        request.spares()
                ));
        if(repair != null){
            return ResponseEntity.ok(repair);
        }else{
            throw new RepairRegistrationFailedException("Repair registration failed.");
        }
    }

    @GetMapping(path = "/repairs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Repair> read(@PathVariable Long id){
        Repair repair = repairReparation.getRepairById(id);
        if(repair!=null){
            return ResponseEntity.ok(repairReparation.getRepairById(id));
        }
        throw new RepairNotFoundException(String.format("Repair with id : %d not found",id));
    }

    @GetMapping(path = "/repairs/reference/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Repair> read(@PathVariable("reference") String reference){
        Repair repair = repairReparation.getRepairByReference(reference);
        if(repair!=null){
            return ResponseEntity.ok(repairReparation.getRepairByReference(reference));
        }
        throw new RepairNotFoundException(String.format("Repair with reference : %s not found",reference));
    }

    @GetMapping(path = "/repairs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Repair>> read(){
        List<Repair> repairs = repairReparation.getAllRepair();
        if(repairs.isEmpty()) {
            return ResponseEntity.ok(repairReparation.getAllRepair());
        }
        throw new RepairNotFoundException("Any repair found");
    }

    @PutMapping(path = "/repairs/{id}")
    public void edit(@RequestBody Repair repair, @PathVariable Long id){
        repairReparation.editRepair(repair,id);
    }

    @PutMapping(path = "/repairs/order/{id}")
    public void order(@PathVariable Long id){
        repairReparation.orderRepair(id);
    }

    @DeleteMapping(path = "/repairs/{id}")
    public void delete(@PathVariable Long id){
        repairReparation.deleteRepair(id);
    }

    @PutMapping(path = "/repairs/task/{id}")
    public void editTask(@RequestBody Task task, @PathVariable Long id){
        repairReparation.editTask(task,id);
    }

    @DeleteMapping(path = "/repairs/task/{id}")
    public void deleteTask(@PathVariable Long id){
        repairReparation.deleteTask(id);
    }

    @PutMapping(path = "/repairs/spare/{id}")
    public void editSpare(@RequestBody Spare spare, @PathVariable Long id){
        repairReparation.editSpare(spare,id);
    }

    @DeleteMapping(path = "/repairs/spare/{id}")
    public void deleteSpare(@PathVariable Long id){
        repairReparation.deleteSpare(id);
    }

    @GetMapping(path = "/repairs/report/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(HttpServletResponse response, @PathVariable Long id) {
        //reportService.produceReportById(response,"repair",id);
    }
}
