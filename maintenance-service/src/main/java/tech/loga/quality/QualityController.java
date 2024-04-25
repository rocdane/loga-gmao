package tech.loga.quality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.loga.repair.Task;

@CrossOrigin
@RestController
@RequestMapping("/maintenance-service")
public class QualityController {

    private final QualityManagement qualityManagement;

    @Autowired
    public QualityController(QualityManagement qualityManagement) {
        this.qualityManagement = qualityManagement;
    }

    @PostMapping(path = "/quality")
    public ResponseEntity<Quality> create(QualityRequest request){
        Quality quality =
                qualityManagement.createQuality(new Quality(
                        request.employee(),
                        request.customer(),
                        request.mileage(),
                        request.description(),
                        request.controls()
                ));
        if(quality!=null){
            return ResponseEntity.ok(quality);
        }else{
            throw new QualityRegistrationFailedException("Quality registration failed");
        }
    }
}
