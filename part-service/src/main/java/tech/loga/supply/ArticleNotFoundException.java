package tech.loga.supply;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ArticleNotFoundException extends RuntimeException{

    public ArticleNotFoundException(String message) {
        super(message);
    }
}
