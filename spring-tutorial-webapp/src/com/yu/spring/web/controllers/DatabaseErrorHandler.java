package com.yu.spring.web.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Globally Handle all Database exceptions gracefully.
 * @author xiaoy
 *
 */
@ControllerAdvice
public class DatabaseErrorHandler {
    
    @ExceptionHandler(DataAccessException.class)
    public String handleDatabaseException(DataAccessException e) {
        return "error";
    }
}
