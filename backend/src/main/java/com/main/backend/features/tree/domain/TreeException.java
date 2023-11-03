package com.main.backend.features.tree.domain;

/**
 * Exception thrown when detecting disallowed actions during tree creation.
 * Typically, an errorMessage is sent to end user to inform him of incorrect action.
 */
public class TreeException extends Exception {
    public TreeException(String errorMessage) {
        super(errorMessage);
    }
}
