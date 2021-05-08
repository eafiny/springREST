package ru.geekbrains.springREST.error_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<MarketError> handleResourceNotFoundException(ResourceNotFoundException e){
        MarketError error = new MarketError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<MarketError> handleInvalidDataException(InvalidDataException e){
        MarketError error = new MarketError(HttpStatus.BAD_REQUEST.value(), e.getMessages());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
