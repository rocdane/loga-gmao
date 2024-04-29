package tech.loga.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmployeeRegistrationFailedException extends RuntimeException {
    public EmployeeRegistrationFailedException(String message) {
        super(message);
    }
}
