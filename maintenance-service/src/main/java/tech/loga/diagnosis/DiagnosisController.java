package tech.loga.diagnosis;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/maintenance-service")
public class DiagnosisController {

    @Autowired
    private DiagnosisManagement repairDiagnostic;

    //private ReportService reportService;

    @PostMapping(path = "/diagnosis", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diagnosis> create(@RequestBody Diagnosis diagnosis) {
        Diagnosis created = repairDiagnostic.createDiagnosis(diagnosis);
        if(created!=null){
            return ResponseEntity.ok(created);
        }else {
            throw new DiagnosisRegistrationFailedException("Failed to registrate diagnosis.");
        }
    }

    @GetMapping(path = "/diagnosis", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Diagnosis> read() {
        return repairDiagnostic.getAllDiagnosis();
    }

    @GetMapping(path = "/diagnosis/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Diagnosis read(@PathVariable Long id) {
        return repairDiagnostic.getDiagnosisById(id);
    }

    @GetMapping(path = "/report/diagnosis/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(HttpServletResponse response, @PathVariable Long id) {
        //reportService.produceReportById(response,"diagnosis",id);
    }

    public String fallBackResponse(Exception ex){
        return "Fall Back response : "+ex.getMessage();
    }
}
