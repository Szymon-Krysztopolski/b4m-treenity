package com.main.backend.features.user.exception;

import com.main.backend.shared.TreenityException;

public class BlankFieldException extends TreenityException {
    public BlankFieldException() {
        super("Fields cannot be blank!");
    }
}
