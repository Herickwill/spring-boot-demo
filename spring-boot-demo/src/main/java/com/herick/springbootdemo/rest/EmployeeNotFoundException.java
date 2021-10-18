package com.herick.springbootdemo.rest;

import javassist.NotFoundException;

public class EmployeeNotFoundException extends NotFoundException {

    public EmployeeNotFoundException(String msg) {
        super(msg);
    }

    public EmployeeNotFoundException(String msg, Exception e) {
        super(msg, e);
    }
}
