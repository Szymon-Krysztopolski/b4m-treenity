package com.main.backend.features.user.exception;

import com.main.backend.shared.TreenityException;

public class InvalidEmailException extends TreenityException {
    public InvalidEmailException() {
        super("Email is not valid!");
    }
}
