package com.main.backend.features.user.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User not exists!");
    }
}
