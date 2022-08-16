package dh.meli.projeto_integrador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Class that extends of the RunTimeException error class. It represents the custom Exception 400 CustomerExistException.
 * @author Thiago Frozzi
 * @version 0.0.1
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerExistException extends RuntimeException {
    /**
     * Constructor method called whether an operation results in an invalid parameter error.
     * @param message custom message displayed to user by the server response.
     */
    public CustomerExistException (String message) {
        super(message);
    }
}
