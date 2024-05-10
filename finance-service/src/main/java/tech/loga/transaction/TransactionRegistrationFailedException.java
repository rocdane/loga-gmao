package tech.loga.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TransactionRegistrationFailedException extends RuntimeException{
    public TransactionRegistrationFailedException(String message) {
        super(message);
    }
}
