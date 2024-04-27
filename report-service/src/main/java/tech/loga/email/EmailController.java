package tech.loga.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/reporting-service")
public class EmailController {

    @Autowired
    private EmailManagement emailManagement;

    @PostMapping(path = "/email", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> sendEmail(@RequestBody EmailMessage emailMessage){
        try {
            emailManagement.sendMessage(emailMessage);
            return ResponseEntity.ok("Message send successfully");
        }catch (Exception e){
            throw new RuntimeException("Email failed\n"+e.getMessage());
        }
    }

    @PostMapping(path = "/email/files", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> sendEmailWithAttachments(@RequestBody EmailMessage emailMessage, @RequestParam MultipartFile[] files){
        try {
            emailManagement.sendMessage(emailMessage, files);
            return ResponseEntity.ok("Message send successfully");
        }catch (Exception e){
            throw new RuntimeException("Email failed\n"+e.getMessage());
        }
    }
}
