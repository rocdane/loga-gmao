package tech.loga.notifier.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
@RequestMapping("/reporting-service")
public class EmailController {

    @Autowired
    private IEmailSenderService emailSenderService;

    @PostMapping(path = "/email", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> sendEmail(@RequestBody EmailMessage emailMessage){
        try {
            emailSenderService.sendMessage(emailMessage);
            return ResponseEntity.ok("Success");
        }catch (Exception e){
            throw new RuntimeException("Email failed\n"+e.getMessage());
        }
    }

    @PostMapping(path = "/email/files", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> sendEmailWithAttachments(@RequestBody EmailMessage emailMessage, @RequestParam MultipartFile[] files){
        emailSenderService.sendMessage(emailMessage, files);
        return ResponseEntity.ok("Success");
    }
}
