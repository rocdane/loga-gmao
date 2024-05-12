package tech.loga.trade;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SaleRegistrationFailedException extends RuntimeException {
    public SaleRegistrationFailedException(String message) {
        super(message);
    }
}
