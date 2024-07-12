package com.example.samplecommerce.application.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Invalid Product.");
    }
}
