package dh.meli.projeto_integrador.handler;

import dh.meli.projeto_integrador.exception.CartAlreadyFinishedException;
import dh.meli.projeto_integrador.exception.CartNotFoundException;
import dh.meli.projeto_integrador.exception.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ExceptionDetails> cartNotFoundHandler(CartNotFoundException exception) {

        return new ResponseEntity<ExceptionDetails>(
                ExceptionDetails.builder()
                        .title("Cart Not Found")
                        .message(exception.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartAlreadyFinishedException.class)
    public ResponseEntity<ExceptionDetails> cartNotFoundHandler(CartAlreadyFinishedException exception) {

        return new ResponseEntity<ExceptionDetails>(
                ExceptionDetails.builder()
                        .title("Cart Already Finished")
                        .message(exception.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.NOT_FOUND);
    }
}
