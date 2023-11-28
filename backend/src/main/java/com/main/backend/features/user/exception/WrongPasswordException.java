package com.main.backend.features.user.exception;

import com.main.backend.shared.TreenityException;

public class WrongPasswordException extends TreenityException {
    public WrongPasswordException() {
        super("Wrong password!");
    }
}
