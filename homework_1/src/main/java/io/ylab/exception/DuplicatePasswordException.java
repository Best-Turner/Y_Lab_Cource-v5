package io.ylab.exception;

public class DuplicatePasswordException extends Exception {
    public DuplicatePasswordException(String message) {
        super(message);
    }
}
