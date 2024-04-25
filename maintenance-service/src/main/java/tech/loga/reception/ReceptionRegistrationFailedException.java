package tech.loga.reception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ReceptionRegistrationFailedException extends RuntimeException {
    public ReceptionRegistrationFailedException(String message) {
        super(message);
    }
}
