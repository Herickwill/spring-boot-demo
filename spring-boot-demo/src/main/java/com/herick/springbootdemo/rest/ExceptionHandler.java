package com.herick.springbootdemo.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException e) {
        EmployeeErrorResponse error = new EmployeeErrorResponse();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
