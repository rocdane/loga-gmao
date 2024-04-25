package tech.loga.reception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReceptionNotFoundException extends RuntimeException{
    public ReceptionNotFoundException(String message) {
        super(message);
    }
}
