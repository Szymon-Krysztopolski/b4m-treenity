package com.main.backend.features.user.exception;

import com.main.backend.shared.TreenityException;

public class EmailAlreadyUsedException extends TreenityException {
    public EmailAlreadyUsedException() {
        super("Email is already used!");
    }
}
