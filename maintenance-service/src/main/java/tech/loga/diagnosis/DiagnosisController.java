package tech.loga.diagnosis;

import jakarta.servlet.http.HttpServletResponse;
import org.loga.maintenance.api.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/maintenance-service")
public class DiagnosisController {

    @Autowired
    private IDiagnosisService repairDiagnostic;

    @Autowired
    private ReportService reportService;

    @PostMapping(path = "/diagnosis", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diagnosis> create(@RequestBody Diagnosis diagnosis) {
        Diagnosis created = repairDiagnostic.create(diagnosis);
        if(created!=null){
            return ResponseEntity.ok(created);
        }else {
            throw new DiagnosisRegistrationFailedException("Failed to registrate diagnosis.");
        }
    }

    @GetMapping(path = "/diagnosis", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Diagnosis> read() {
        return repairDiagnostic.readAll();
    }

    @GetMapping(path = "/diagnosis/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Diagnosis read(@PathVariable Long id) {
        return repairDiagnostic.read(id);
    }

    @GetMapping(path = "/report/diagnosis/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(HttpServletResponse response, @PathVariable Long id) {
        reportService.produceReportById(response,"diagnosis",id);
    }

    public String fallBackResponse(Exception ex){
        return "Fall Back response : "+ex.getMessage();
    }
}
