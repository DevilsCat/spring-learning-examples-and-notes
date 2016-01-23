package com.yu.spring.web.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Globally Handle all Database exceptions gracefully.
 * @author xiaoy
 *
 */
@ControllerAdvice
public class ErrorHandler {
    
    @ExceptionHandler(DataAccessException.class)
    public String handleDatabaseException(DataAccessException e) {
        e.printStackTrace();
        return "error";
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessException(AccessDeniedException e) {
        return "denied";
    }
}
