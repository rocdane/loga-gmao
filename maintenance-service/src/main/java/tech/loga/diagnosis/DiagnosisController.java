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

    private final DiagnosisManagement diagnosisManagement;

    @Autowired
    public DiagnosisController(DiagnosisManagement diagnosisManagement) {
        this.diagnosisManagement = diagnosisManagement;
    }

    //private ReportService reportService;

    @PostMapping(path = "/diagnosis", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diagnosis> create(@RequestBody DiagnosisRequest request) {
        Diagnosis created =
                diagnosisManagement.createDiagnosis(new Diagnosis(
                        request.employee(),
                        request.customer(),
                        request.mileage(),
                        request.description(),
                        request.factors()
                ));
        if(created!=null){
            return ResponseEntity.ok(created);
        }
        throw new DiagnosisRegistrationFailedException("Failed to registrate diagnosis.");
    }

    @GetMapping(path = "/diagnosis", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Diagnosis>> read() {
        List<Diagnosis> diagnoses = diagnosisManagement.getAllDiagnosis();
        if(diagnoses.isEmpty()){
            throw new DiagnosisNotFoundException("Any diagnosis found");
        }
        return ResponseEntity.ok(diagnoses);
    }

    @GetMapping(path = "/diagnosis/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diagnosis> read(@PathVariable Long id) {
        Diagnosis diagnosis = diagnosisManagement.getDiagnosisById(id);
        if(diagnosis!=null){
            return ResponseEntity.ok(diagnosis);
        }
        throw new DiagnosisNotFoundException(String.format("Diagnosis with id : %d not found",id));
    }

    @GetMapping(path = "/diagnosis/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diagnosis> read(@PathVariable String reference) {
        Diagnosis diagnosis = diagnosisManagement.getDiagnosisByReference(reference);
        if(diagnosis!=null){
            return ResponseEntity.ok(diagnosis);
        }
        throw new DiagnosisNotFoundException(String.format("Diagnosis with reference : %s not found",reference));
    }

    @GetMapping(path = "/diagnosis/report/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(HttpServletResponse response, @PathVariable Long id) {
        //reportService.produceReportById(response,"diagnosis",id);
    }

    public String fallBackResponse(Exception ex){
        return "Fall Back response : "+ex.getMessage();
    }
}
