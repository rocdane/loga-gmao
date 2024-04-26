package tech.loga.notifier.email;

import org.springframework.web.multipart.MultipartFile;

public interface IEmailSenderService {
    void sendMessage(EmailMessage emailMessage);
    void sendMessage(EmailMessage emailMessage, MultipartFile[] files);
}
