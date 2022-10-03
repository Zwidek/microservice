package com.zwidek.customer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class CustomerControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> noConnectionHandler(RuntimeException error) {
        log.error("No connection {}", error.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> validationFailHandler(ConstraintViolationException error) {
        log.error("Error with validation: {}", error.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error.getMessage());
    }
}
