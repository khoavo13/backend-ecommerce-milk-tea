package com.example.backend_ecommerce_milk_tea.excepations;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
