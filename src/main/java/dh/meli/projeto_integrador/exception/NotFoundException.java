package dh.meli.projeto_integrador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Class that extends of the RunTimeException error class. It represents the custom NotFoundException.
 * @author Rafael Cavalcante.
 * @version 0.0.1
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    /**
     * Call super and pass the param error message when something doesn't exists in the Database.
     * @param message (String)
     */
    public NotFoundException(String message) {
        super(message);
    }

}
