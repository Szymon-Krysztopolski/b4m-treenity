package com.main.backend.features.token.exception;

public class TokenHasBeenUsedException extends Exception {
    public TokenHasBeenUsedException() {
        super("Token has been already used!");
    }
}
