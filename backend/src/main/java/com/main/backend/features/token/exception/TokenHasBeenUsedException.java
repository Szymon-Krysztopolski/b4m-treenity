package com.main.backend.features.token.exception;

import com.main.backend.shared.TreenityException;

public class TokenHasBeenUsedException extends TreenityException {
    public TokenHasBeenUsedException() {
        super("Token has been already used!");
    }
}
