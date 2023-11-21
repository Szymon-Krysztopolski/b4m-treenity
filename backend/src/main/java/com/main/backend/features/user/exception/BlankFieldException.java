package com.main.backend.features.user.exception;

public class BlankFieldException extends Exception {
    public BlankFieldException() {
        super("Fields cannot be blank!");
    }
}
