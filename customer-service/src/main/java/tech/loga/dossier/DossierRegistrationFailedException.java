package tech.loga.dossier;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DossierRegistrationFailedException extends RuntimeException{
    public DossierRegistrationFailedException(String message) {
        super(message);
    }
}
