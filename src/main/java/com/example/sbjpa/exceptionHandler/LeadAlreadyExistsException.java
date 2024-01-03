package com.example.sbjpa.exceptionHandler;
public class LeadAlreadyExistsException  extends RuntimeException {

    public LeadAlreadyExistsException(String message) {
        super(message);
    }
}
