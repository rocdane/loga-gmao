package tech.loga.quality;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.loga.api.ReportService;
import tech.loga.repair.Task;

@CrossOrigin
@RestController
@RequestMapping("/maintenance-service")
public class QualityController {

    private final QualityManagement qualityManagement;
    private final ReportService reportService;

    @Autowired
    public QualityController(QualityManagement qualityManagement,
                             ReportService reportService) {
        this.qualityManagement = qualityManagement;
        this.reportService = reportService;
    }

    @PostMapping(path = "/quality")
    public ResponseEntity<Quality> create(@RequestBody QualityRequest request){
        try {
            Quality quality =
                    qualityManagement.createQuality(new Quality(
                            request.employee(),
                            request.customer(),
                            request.mileage(),
                            request.description(),
                            request.controls()
                    ));
            return ResponseEntity.ok(quality);
        }catch (Exception e){
            throw new QualityRegistrationFailedException("Quality registration failed");
        }
    }

    @GetMapping(path = "/quality/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Quality> getReport(@PathVariable Long id){
        try {
            Quality quality = qualityManagement.getQualityById(id);
            return ResponseEntity.ok(quality);
        }catch (Exception e){
            throw new QualityNotFoundException(String.format("Quality with id : %d not found",id));
        }
    }

    @GetMapping(path = "/quality/report/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(@PathVariable Long id, HttpServletResponse response){
        try {
            Quality quality =
                    qualityManagement.getQualityById(id);
            reportService.report(quality,"quality",response);
        }catch (Exception e){
            throw new QualityRegistrationFailedException("Quality report failed");
        }
    }
}
