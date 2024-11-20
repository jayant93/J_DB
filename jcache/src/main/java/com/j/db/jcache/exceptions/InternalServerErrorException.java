package com.j.db.jcache.exceptions;

/**
 * To handle all generic internal server error exceptions
 */
public class InternalServerErrorException extends RuntimeException{

    public InternalServerErrorException(String ex){
        super(ex);
    }

    public InternalServerErrorException(String ex,Throwable cause){
        super(ex,cause);
    }
}
