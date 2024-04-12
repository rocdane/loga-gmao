package tech.loga.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class SessionErrorException extends RuntimeException {

    public SessionErrorException(String message) {
        super(message);
    }
}
