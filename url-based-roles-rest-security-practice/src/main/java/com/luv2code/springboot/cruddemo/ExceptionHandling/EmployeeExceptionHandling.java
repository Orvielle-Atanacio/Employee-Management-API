package com.luv2code.springboot.cruddemo.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandling {    

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exc) {
        // create ErrorResponse
        ErrorResponse error = new ErrorResponse(
                exc.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(EmployeeNotFoundException exc) {
        // create ErrorResponse
        ErrorResponse error = new ErrorResponse(
                exc.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
