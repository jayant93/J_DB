package com.j.db.jcache.exceptions;

public class InvalidTTLException extends RuntimeException {
    public InvalidTTLException(String message) {
        super(message);
    }
}
