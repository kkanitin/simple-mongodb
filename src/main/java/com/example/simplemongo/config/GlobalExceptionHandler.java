package com.example.simplemongo.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value
            = {NoSuchElementException.class})
    protected ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        return ResponseEntity.notFound().build();
    }
}
