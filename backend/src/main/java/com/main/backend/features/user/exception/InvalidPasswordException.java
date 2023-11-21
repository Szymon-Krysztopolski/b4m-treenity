package com.main.backend.features.user.exception;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException() {
        super("Password is invalid!");
    }
}
