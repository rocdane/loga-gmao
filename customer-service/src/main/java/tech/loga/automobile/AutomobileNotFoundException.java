package tech.loga.automobile;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AutomobileNotFoundException extends RuntimeException{

    public AutomobileNotFoundException(String message) {
        super(message);
    }
}
