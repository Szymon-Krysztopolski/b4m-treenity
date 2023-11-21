package com.main.backend.features.user.exception;

import com.main.backend.shared.TreenityException;

public class UserNotFoundException extends TreenityException {
    public UserNotFoundException() {
        super("User not exists!");
    }
}
