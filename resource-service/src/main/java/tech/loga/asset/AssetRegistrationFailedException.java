package tech.loga.asset;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AssetRegistrationFailedException extends RuntimeException {
    public AssetRegistrationFailedException(String message) {
        super(message);
    }
}
