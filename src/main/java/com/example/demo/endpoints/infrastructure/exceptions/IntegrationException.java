package com.example.demo.endpoints.infrastructure.exceptions;

public class IntegrationException extends RuntimeException{

    public IntegrationException(String message) {
        super(message);
    }
}
