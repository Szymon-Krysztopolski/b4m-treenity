package com.main.backend.features.token.exception;

public class TokenHasExpiredException extends Exception {
    public TokenHasExpiredException() {
        super("Token has expired!");
    }
}
