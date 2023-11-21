package com.main.backend.features.token.exception;

public class EmptyMailLinkException extends Exception {
    public EmptyMailLinkException() {
        super("Mail website link is empty!");
    }
}
