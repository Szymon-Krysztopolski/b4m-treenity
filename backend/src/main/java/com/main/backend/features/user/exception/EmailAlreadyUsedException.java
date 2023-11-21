package com.main.backend.features.user.exception;

public class EmailAlreadyUsedException extends Exception {
    public EmailAlreadyUsedException() {
        super("Email is already used!");
    }
}
