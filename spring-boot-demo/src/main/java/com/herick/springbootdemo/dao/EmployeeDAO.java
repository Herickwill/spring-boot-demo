package com.herick.springbootdemo.dao;

import com.herick.springbootdemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    Employee findById(long id);

    Employee findByName(String name);

    void save (Employee employee);

    void deleteById(long id);
}
