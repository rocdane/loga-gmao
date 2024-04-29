package tech.loga.company;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CompanyRegistrationFailedException extends RuntimeException{
    public CompanyRegistrationFailedException(String message) {
        super(message);
    }
}
