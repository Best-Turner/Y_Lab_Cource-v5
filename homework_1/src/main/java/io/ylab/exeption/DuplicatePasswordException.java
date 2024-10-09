package io.ylab.exeption;

public class DuplicatePasswordException extends Exception {
    public DuplicatePasswordException(String message) {
        super(message);
    }
}
