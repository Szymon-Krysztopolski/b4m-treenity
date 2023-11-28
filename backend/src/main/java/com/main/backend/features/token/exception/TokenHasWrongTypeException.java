package com.main.backend.features.token.exception;

import com.main.backend.shared.TreenityException;

public class TokenHasWrongTypeException extends TreenityException {
    public TokenHasWrongTypeException() {
        super("Token has wrong type!");
    }
}
