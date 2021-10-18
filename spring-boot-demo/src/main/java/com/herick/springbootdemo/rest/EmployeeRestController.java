package com.herick.springbootdemo.rest;

import com.herick.springbootdemo.dao.EmployeeDAO;
import com.herick.springbootdemo.entity.Employee;
import com.herick.springbootdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    private List<Employee> getAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{id}")
    private Employee getById(@PathVariable long id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeService.findById(id);
        if(!employee.isPresent()) {
            throw new EmployeeNotFoundException("Employee with id " +id+ " not found.");
        }
        return employee.get();
    }

    @PostMapping("/employee")
    private HttpStatus addEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        employeeService.save(employee);
        return HttpStatus.CREATED;
    }
    @PutMapping("/employee")
    private HttpStatus updateEmployee(@RequestBody Employee employee){
        employeeService.save(employee);
        return HttpStatus.OK;
    }

    @DeleteMapping("/employee/{id}")
    private HttpStatus deleteEmployee(@PathVariable long id) {
        employeeService.deleteById(id);
        return HttpStatus.OK;
    }

}
