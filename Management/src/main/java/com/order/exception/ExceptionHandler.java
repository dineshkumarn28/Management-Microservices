package com.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NoOrderException.class)
    public ResponseEntity<Object> handleExceptions( NoOrderException exception, WebRequest webRequest) {
        ResponseEntity<Object> entity = new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        return entity;
    }
}