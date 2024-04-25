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
    private ReceptionManagement receptionManagement;

    //private ReportService reportService;

    @PostMapping(path = "/receptions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reception> create(@RequestBody ReceptionRequest request){
        Reception reception = receptionManagement.createReception(new Reception(
                        request.customer(),
                        request.employee(),
                        request.mileage(),
                        request.description(),
                        request.notices()
        ));
        if(reception!=null){
            return ResponseEntity.ok(reception);
        }else{
            throw new ReceptionRegistrationFailedException("Reception registration failed");
        }

    }

    @GetMapping(path = "/receptions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reception>> read() {
        return ResponseEntity.ok(receptionManagement.getAllReception());
    }

    @GetMapping(path = "/receptions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reception> read(@PathVariable Long id){
        return ResponseEntity.ok(receptionManagement.getReceptionById(id));
    }

    @GetMapping(path = "/receptions/report/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(HttpServletResponse response, @PathVariable Long id) {
        //reportService.produceReportById(response,"reception",id);
    }
}
