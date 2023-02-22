package com.api.vetgroup.exceptions.handler;

import com.api.vetgroup.exceptions.ExceptionResponse;
import com.api.vetgroup.exceptions.JwtExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(JwtExpiredException.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        ExceptionResponse errorResponse = new ExceptionResponse(new Date(), ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(Exception ex) {
        ExceptionResponse errorResponse = new ExceptionResponse(new Date(), ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}