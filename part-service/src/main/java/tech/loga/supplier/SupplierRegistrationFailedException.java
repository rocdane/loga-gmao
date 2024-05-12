package tech.loga.supplier;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SupplierRegistrationFailedException extends RuntimeException{
    public SupplierRegistrationFailedException(String message) {
        super(message);
    }
}
