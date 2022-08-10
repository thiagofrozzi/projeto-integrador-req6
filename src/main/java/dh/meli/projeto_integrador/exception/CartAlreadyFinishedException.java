package dh.meli.projeto_integrador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CartAlreadyFinishedException extends RuntimeException {
    public CartAlreadyFinishedException (String message) {
        super(message);
    }
}
