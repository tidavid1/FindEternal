package com.tidavid1.FindEternal.domain.user.exception;

public class CUserNotFoundException extends RuntimeException{
    public CUserNotFoundException() {
    }

    public CUserNotFoundException(String message) {
        super(message);
    }

    public CUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
