package com.j.db.jcache.exceptions;

public class CacheEmptyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // Constructor with message
    public CacheEmptyException(String message) {
        super(message);
    }

    // Constructor with message and cause
    public CacheEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
