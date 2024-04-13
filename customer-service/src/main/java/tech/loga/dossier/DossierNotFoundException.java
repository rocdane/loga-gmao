package tech.loga.dossier;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DossierNotFoundException extends RuntimeException{

    public DossierNotFoundException(String message) {
        super(message);
    }
}
