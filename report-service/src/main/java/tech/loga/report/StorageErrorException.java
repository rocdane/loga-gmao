package tech.loga.report;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StorageErrorException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public StorageErrorException(String message) {
        super(message);
    }

    public StorageErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
