package com.main.backend.features.token.exception;

import com.main.backend.shared.TreenityException;

public class TokenNotFoundException extends TreenityException {
    public TokenNotFoundException() {
        super("Token not found!");
    }
}
