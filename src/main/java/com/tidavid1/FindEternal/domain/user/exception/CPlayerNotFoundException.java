package com.tidavid1.FindEternal.domain.user.exception;

public class CPlayerNotFoundException extends RuntimeException{
    public CPlayerNotFoundException() {
    }

    public CPlayerNotFoundException(String message) {
        super(message);
    }

    public CPlayerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
