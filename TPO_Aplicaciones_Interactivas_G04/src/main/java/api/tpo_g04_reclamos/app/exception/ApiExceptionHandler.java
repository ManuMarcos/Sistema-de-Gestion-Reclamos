package api.tpo_g04_reclamos.app.exception;

import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> handleItemNotFound(Exception e) {

        return new ResponseEntity<>("Error: " + e.getMessage(), NOT_FOUND);
    }

}
