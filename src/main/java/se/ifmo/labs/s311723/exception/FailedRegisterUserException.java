package se.ifmo.labs.s311723.exception;

public class FailedRegisterUserException extends RuntimeException {
    public FailedRegisterUserException(String message) {
        super(message);
    }
}
