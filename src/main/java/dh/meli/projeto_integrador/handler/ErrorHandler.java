package dh.meli.projeto_integrador.handler;

import dh.meli.projeto_integrador.exception.ExceptionDetails;
import dh.meli.projeto_integrador.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandler {

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
