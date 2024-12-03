package tech.loga.supply.order;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class OrderRegistrationFailedException extends RuntimeException{
    public OrderRegistrationFailedException(String message) {
        super(message);
    }
}
