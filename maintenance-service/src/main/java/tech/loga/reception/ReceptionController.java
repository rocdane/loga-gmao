package tech.loga.reception;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/maintenance-service")
public class ReceptionController {

    @Autowired
    private ReceptionManagement repairReception;

    //private ReportService reportService;

    @PostMapping(path = "/receptions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reception> create(@RequestBody Reception reception){
        return ResponseEntity.ok(repairReception.createReception(reception));
    }

    @GetMapping(path = "/receptions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reception> read() {
        return repairReception.getAllReception();
    }

    @GetMapping(path = "/receptions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reception read(@PathVariable Long id){
        return repairReception.getReceptionById(id);
    }

    @GetMapping(path = "/report/reception/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(HttpServletResponse response, @PathVariable Long id) {
        //reportService.produceReportById(response,"reception",id);
    }
}
