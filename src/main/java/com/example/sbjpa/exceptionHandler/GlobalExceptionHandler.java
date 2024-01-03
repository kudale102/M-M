package com.example.sbjpa.exceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.sbjpa.entity.Reponse;

import com.example.sbjpa.entity.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LeadAlreadyExistsException.class)
    public ResponseEntity<Object> handleLeadAlreadyExistsException(LeadAlreadyExistsException ex) {

        ErrorResponse errorResponse = new ErrorResponse("E10010", new String[] {ex.getMessage()});
        Reponse apiResponse = new Reponse("error", errorResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(NoLeadFoundException.class)
    public ResponseEntity<Object> handleNoLeadFoundException(NoLeadFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse("E10011", new String[] {ex.getMessage()});
        Reponse apiResponse = new Reponse("error", errorResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
