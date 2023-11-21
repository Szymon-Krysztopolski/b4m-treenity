package com.main.backend.features.token.exception;

public class TokenHasWrongTypeException extends Exception {
    public TokenHasWrongTypeException() {
        super("Token has wrong type!");
    }
}
