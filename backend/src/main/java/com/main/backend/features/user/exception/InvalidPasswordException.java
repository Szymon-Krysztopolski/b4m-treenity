package com.main.backend.features.user.exception;

import com.main.backend.shared.TreenityException;

public class InvalidPasswordException extends TreenityException {
    public InvalidPasswordException() {
        super("Password is invalid!");
    }
}
