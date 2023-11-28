package com.main.backend.features.token.exception;

import com.main.backend.shared.TreenityException;

public class EmptyMailLinkException extends TreenityException {
    public EmptyMailLinkException() {
        super("Mail website link is empty!");
    }
}
