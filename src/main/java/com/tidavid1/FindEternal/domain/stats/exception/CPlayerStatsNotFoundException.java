package com.tidavid1.FindEternal.domain.stats.exception;

public class CPlayerStatsNotFoundException extends RuntimeException{
    public CPlayerStatsNotFoundException() {
    }

    public CPlayerStatsNotFoundException(String message) {
        super(message);
    }

    public CPlayerStatsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
