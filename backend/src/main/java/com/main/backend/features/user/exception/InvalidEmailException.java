package com.main.backend.features.user.exception;

public class InvalidEmailException extends Exception {
    public InvalidEmailException() {
        super("Email is not valid!");
    }
}
