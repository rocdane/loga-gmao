package tech.loga.reception;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.loga.api.ReportService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/maintenance-service")
public class ReceptionController {

    private final ReceptionManagement receptionManagement;
    private final ReportService reportService;

    @Autowired
    public ReceptionController(ReceptionManagement receptionManagement,
                               ReportService reportService) {
        this.receptionManagement = receptionManagement;
        this.reportService = reportService;
    }

    @PostMapping(path = "/receptions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reception> create(@RequestBody ReceptionRequest request){
        try {
            Reception reception = receptionManagement.createReception(new Reception(
                    request.customer(),
                    request.employee(),
                    request.mileage(),
                    request.description(),
                    request.notices()
            ));
            return ResponseEntity.ok(reception);
        }catch (Exception e) {
            throw new ReceptionRegistrationFailedException("Reception registration failed : \n"+e.getMessage());
        }
    }

    @GetMapping(path = "/receptions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reception>> read() {
        try {
            List<Reception> receptions = receptionManagement.getAllReception();
            return ResponseEntity.ok(receptions);
        }catch (Exception e){
            throw new ReceptionNotFoundException("Failed to fetch reception list");
        }
    }

    @GetMapping(path = "/receptions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reception> read(@PathVariable Long id){
        try {
            Reception reception = receptionManagement.getReceptionById(id);
            return ResponseEntity.ok(reception);
        }catch (Exception e){
            throw new ReceptionNotFoundException(String.format("Reception with id : %d not found : %s",id,e.getMessage()));
        }
    }

    @GetMapping(path = "/receptions/report/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(@PathVariable Long id, HttpServletResponse response) {
        try {
            Reception reception = receptionManagement.getReceptionById(id);
            reportService.report(reception,"reception",response);
        }catch (Exception e){
            throw new ReceptionNotFoundException("Reception report failed : \n"+e.getMessage());
        }
    }
}
