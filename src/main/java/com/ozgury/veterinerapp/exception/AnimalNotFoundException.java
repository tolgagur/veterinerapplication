package com.ozgury.veterinerapp.exception;

public class AnimalNotFoundException extends Throwable {
    public AnimalNotFoundException(String message) {
        super(message);
    }
}
