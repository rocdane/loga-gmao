package tech.loga.quality;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QualityNotFoundException extends RuntimeException{
    public QualityNotFoundException(String message) {
        super(message);
    }
}
