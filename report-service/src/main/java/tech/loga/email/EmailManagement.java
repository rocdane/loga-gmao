package tech.loga.email;

import org.springframework.web.multipart.MultipartFile;

public interface EmailManagement {
    void sendMessage(EmailMessage emailMessage);
    void sendMessage(EmailMessage emailMessage, MultipartFile[] files);
}
