package br.com.devpraticar.rinhabackend.config;

import br.com.devpraticar.rinhabackend.rest.exception.BadRequestException;
import br.com.devpraticar.rinhabackend.rest.exception.UnprocessableEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RinhaExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<HttpStatus> badRequestException() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<HttpStatus> unprocessableEntityException() {
        return ResponseEntity.unprocessableEntity().build();
    }

}
