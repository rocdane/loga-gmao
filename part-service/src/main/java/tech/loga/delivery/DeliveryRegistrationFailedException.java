package tech.loga.delivery;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DeliveryRegistrationFailedException extends RuntimeException{
    public DeliveryRegistrationFailedException(String message) {
        super(message);
    }
}
