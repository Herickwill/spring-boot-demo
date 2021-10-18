package com.herick.springbootdemo.service;

import com.herick.springbootdemo.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAll();

    Optional<Employee> findById(long id);

    void save (Employee employee);

    void deleteById(long id);

}
