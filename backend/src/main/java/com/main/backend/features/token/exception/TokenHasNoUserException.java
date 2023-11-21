package com.main.backend.features.token.exception;

public class TokenHasNoUserException extends Exception {
    public TokenHasNoUserException() {
        super("Token has no user!");
    }
}
