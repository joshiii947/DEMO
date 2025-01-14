package com.allica.demo.controller;


import com.allica.demo.entity.ErrorResponse;
import com.allica.demo.exception.BaseRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseRequestException.class)
    public ResponseEntity<?> handleException(BaseRequestException baseRequestException) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), baseRequestException.getMessage(), "Error occured");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
