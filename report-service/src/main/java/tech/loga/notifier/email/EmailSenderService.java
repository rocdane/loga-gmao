package tech.loga.notifier.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
public class EmailSenderService implements IEmailSenderService{

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMessage(EmailMessage emailMessage){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(emailMessage.getSubject());
        mailMessage.setTo(emailMessage.getTo());
        mailMessage.setText(emailMessage.getMessage());
        try {
            this.javaMailSender.send(mailMessage);
        }catch (Exception e){
            log.error(String.format("Failed sending email: {}",e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMessage(EmailMessage emailMessage, MultipartFile[] files) throws RuntimeException{
        try {
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper  = new MimeMessageHelper(message, true, "UTF_8");
            helper.setPriority(1);
            helper.setTo(emailMessage.getTo());
            helper.setSubject(emailMessage.getSubject());
            helper.setText(emailMessage.getMessage());
            for (MultipartFile file : files) {
                FileSystemResource resource = new FileSystemResource(new File(file.getResource().getURI()));
                helper.addAttachment(Objects.requireNonNull(resource.getFilename()),resource);
            }
            javaMailSender.send(message);
        } catch (MessagingException | IOException e) {
            log.error(String.format("Failed sending email: {}",e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    private MimeMessage getMimeMessage(){
        return javaMailSender.createMimeMessage();
    }
}
