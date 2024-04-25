package tech.loga.repair;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RepairRegistrationFailedException extends RuntimeException{
    public RepairRegistrationFailedException(String message) {
        super(message);
    }
}
