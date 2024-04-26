package tech.loga.report;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ReportErrorException extends RuntimeException{

    public ReportErrorException(String message) {
        super(message);
    }
}
