package tech.loga.supply;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FurnisherNotFoundException extends RuntimeException{

    public FurnisherNotFoundException(String message) {
        super(message);
    }
}
