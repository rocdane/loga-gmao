package tech.loga.repair;

import jakarta.servlet.http.HttpServletResponse;
import org.loga.maintenance.api.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/maintenance-service")
public class RepairController {

    @Autowired
    private IRepairService repairReparation;

    @Autowired
    private ReportService reportService;

    @PostMapping(path = "/repairs", produces = MediaType.APPLICATION_JSON_VALUE)
    public Repair create(@RequestBody Repair repair){
        Repair created = repairReparation.createRepair(repair);
        if(created != null){
            return created;
        }else{
            throw new RepairRegistrationFailedException("Failed to registrate repair.");
        }
    }

    @GetMapping(path = "/repairs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Repair read(@PathVariable Long id){
        return repairReparation.findRepair(id);
    }

    @GetMapping(path = "/repairs/reference/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Repair read(@PathVariable("reference") String reference){
        return repairReparation.findRepair(reference);
    }

    @GetMapping(path = "/repairs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Repair> read(){
        return repairReparation.listRepair();
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

    @GetMapping(path = "/report/repair/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(HttpServletResponse response, @PathVariable Long id) {
        reportService.produceReportById(response,"repair",id);
    }
}
