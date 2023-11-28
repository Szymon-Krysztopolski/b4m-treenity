package com.main.backend.features.token.exception;

import com.main.backend.shared.TreenityException;

public class TokenHasNoUserException extends TreenityException {
    public TokenHasNoUserException() {
        super("Token has no user!");
    }
}
