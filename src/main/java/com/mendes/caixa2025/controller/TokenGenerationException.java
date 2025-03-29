package com.mendes.caixa2025.controller;

public class TokenGenerationException extends RuntimeException {
    public TokenGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}