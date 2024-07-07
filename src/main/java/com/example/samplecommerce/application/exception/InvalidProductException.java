package com.example.samplecommerce.application.exception;

public class InvalidProductException extends RuntimeException {
    public InvalidProductException() {
        super("Invalid Product.");
    }
}
