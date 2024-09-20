package com.example.backend_ecommerce_milk_tea.exceptions;

import java.io.IOException;

public class ResourceNotFoundException extends IOException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
