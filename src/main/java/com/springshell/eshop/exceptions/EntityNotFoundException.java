package com.springshell.eshop.exceptions;

public class EntityNotFoundException extends RuntimeException{

    private String message;

    public EntityNotFoundException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public EntityNotFoundException(String message) {
        this.message = message;
    }
}
