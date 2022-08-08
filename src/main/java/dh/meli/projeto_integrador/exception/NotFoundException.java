package dh.meli.projeto_integrador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


    /**
     * Class to extends RunTimeException and send message through super constructor
     * @author Thiago Guimarães, Thiago Frozzi, Gabriela Azevedo, Diovana Valim, Rafael Cavalcante e Amanda Marinelli
     * @version 0.0.1
     */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    /**
     * Call super and pass the param error message about a not found product/client
     * @param message (String)
     */
    public NotFoundException(String message) {
        super(message);
    }

}
