package tech.loga.diagnosis;

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
public class DiagnosisController {

    private final DiagnosisManagement diagnosisManagement;
    private final ReportService reportService;

    @Autowired
    public DiagnosisController(DiagnosisManagement diagnosisManagement,
                               ReportService reportService) {
        this.diagnosisManagement = diagnosisManagement;
        this.reportService = reportService;
    }

    @PostMapping(path = "/diagnosis", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diagnosis> create(@RequestBody DiagnosisRequest request) {
        try {
            Diagnosis diagnosis =
                    diagnosisManagement.createDiagnosis(new Diagnosis(
                            request.employee(),
                            request.customer(),
                            request.mileage(),
                            request.description(),
                            request.factors()
                    ));
            return ResponseEntity.ok(diagnosis);
        }catch (Exception e){
            throw new DiagnosisRegistrationFailedException("Repair registration failed.");
        }
    }

    @GetMapping(path = "/diagnosis", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Diagnosis>> read() {
        try {
            List<Diagnosis> diagnoses = diagnosisManagement.getAllDiagnosis();
            return ResponseEntity.ok(diagnoses);
        }catch (Exception e){
            throw new DiagnosisNotFoundException("Any diagnosis found : \n"+e.getMessage());
        }
    }

    @GetMapping(path = "/diagnosis/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diagnosis> read(@PathVariable Long id) {
        try {
            Diagnosis diagnosis = diagnosisManagement.getDiagnosisById(id);
            return ResponseEntity.ok(diagnosis);
        }catch (Exception e){
            throw new DiagnosisNotFoundException(String.format("Diagnosis with id : %d not found",id));
        }
    }

    @GetMapping(path = "/diagnosis/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diagnosis> read(@PathVariable String reference) {
        try {
            Diagnosis diagnosis = diagnosisManagement.getDiagnosisByReference(reference);
            return ResponseEntity.ok(diagnosis);
        }catch (Exception e){
            throw new DiagnosisNotFoundException(String.format("Diagnosis with reference : %s not found",reference));
        }
    }

    @GetMapping(path = "/diagnosis/report/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(HttpServletResponse response, @PathVariable Long id) {
        try {
            Diagnosis diagnosis = diagnosisManagement.getDiagnosisById(id);
            this.reportService.report(diagnosis,"diagnosis",response);
        }catch (Exception e){
            throw new DiagnosisNotFoundException(String.format("Diagnosis with id :%d reporting failed : \n%s",id,e.getMessage()));
        }
    }
}
