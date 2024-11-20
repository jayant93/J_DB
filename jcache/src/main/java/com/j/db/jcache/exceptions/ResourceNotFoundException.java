package com.j.db.jcache.exceptions;

/**
 * @author jayant kumar
 * Resource not found exception class
 */
public class ResourceNotFoundException extends RuntimeException{


    private static final long serialVersionUID = 1L;

    // Optional: Add a custom message and/or a cause
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
