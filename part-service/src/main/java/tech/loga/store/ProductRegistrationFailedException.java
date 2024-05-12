package tech.loga.store;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ProductRegistrationFailedException extends RuntimeException{
    public ProductRegistrationFailedException(String message) {
        super(message);
    }
}
