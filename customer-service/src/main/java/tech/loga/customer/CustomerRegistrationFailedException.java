package tech.loga.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomerRegistrationFailedException extends RuntimeException{
    public CustomerRegistrationFailedException(String message) {
        super(message);
    }
}
