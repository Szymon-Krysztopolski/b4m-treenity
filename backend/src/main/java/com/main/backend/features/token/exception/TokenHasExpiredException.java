package com.main.backend.features.token.exception;

import com.main.backend.shared.TreenityException;

public class TokenHasExpiredException extends TreenityException {
    public TokenHasExpiredException() {
        super("Token has expired!");
    }
}
