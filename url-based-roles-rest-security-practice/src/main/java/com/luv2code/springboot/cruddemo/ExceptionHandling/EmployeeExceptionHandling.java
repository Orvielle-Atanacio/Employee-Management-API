package com.luv2code.springboot.cruddemo.ExceptionHandling;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exc) {
        String message = exc.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
        ErrorResponse error = new ErrorResponse(message, HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
