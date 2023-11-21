package com.main.backend.features.token.exception;

public class TokenNotFoundException extends Exception {
    public TokenNotFoundException() {
        super("Token not found!");
    }
}
