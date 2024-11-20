package com.j.db.jcache.exceptions.handler;

import com.j.db.jcache.exceptions.CacheEmptyException;
import com.j.db.jcache.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author jayantkumar
 * This class will handle all the
 * exceptions being thrown by the controller
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * ResourceNotFound Exception Handler
     * @param ex
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity resourceNotFound(ResourceNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    /**
     * EmptyCache Exception handler
     * @param ex
     * @return
     */
    @ExceptionHandler(CacheEmptyException.class)
    public ResponseEntity cacheEmpty(CacheEmptyException ex){
        return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

}
