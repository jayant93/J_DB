package com.j.db.jcache.exceptions.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.j.db.jcache.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    /**
     * InternalServerError Exception Handler
     * @param ex
     * @return
     */
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity internalError(InternalServerErrorException ex){
        return new ResponseEntity(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Some Exceptions those are thrown before the request hits my api method
     *
     */

    // Handle invalid TTL exception
    @ExceptionHandler(InvalidTTLException.class)
    public ResponseEntity<String> handleInvalidTTLException(InvalidTTLException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Handle JsonMappingException (e.g., bad TTL value) during deserialization
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleInvalidFormatException(InvalidFormatException ex) {
        return new ResponseEntity<>("Invalid value for TTL: " + ex.getValue(), HttpStatus.BAD_REQUEST);
    }

    // Handle general HTTP message parse errors
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>("Malformed JSON request or invalid TTL value", HttpStatus.BAD_REQUEST);
    }
}
