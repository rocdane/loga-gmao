package tech.loga.quality;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class QualityRegistrationFailedException extends RuntimeException {
    public QualityRegistrationFailedException(String message) {
        super(message);
    }
}
