package dh.meli.projeto_integrador.handler;

import dh.meli.projeto_integrador.exception.ExceptionDetails;
import dh.meli.projeto_integrador.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Class that handle the exceptions that are thrown over the code execution flow.
 * @author Diovana Valim, Rafael Cavalcante
 * @version 0.0.1
 */
@ControllerAdvice
public class ErrorHandler {

    /**
     * Method that captures a NotFoundException and build a response to send through HTTP request.
     * @param ex instance of NotFoundException class captured during the code execution flow.
     * @return a ResponseEntity containing details of the exception and a compatible HTTP status code.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(NotFoundException ex) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Object Not Found")
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.NOT_FOUND);
    }

}
