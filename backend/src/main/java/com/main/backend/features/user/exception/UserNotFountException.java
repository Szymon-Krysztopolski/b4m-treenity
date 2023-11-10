package com.main.backend.features.user.exception;

public class UserNotFountException extends Exception {
    public UserNotFountException(String errorMessage) {
        super(errorMessage);
    }
}
