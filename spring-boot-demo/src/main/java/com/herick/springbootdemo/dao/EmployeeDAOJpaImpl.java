package com.herick.springbootdemo.dao;

import com.herick.springbootdemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements  EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
        List<Employee> employees = theQuery.getResultList();
        return employees;
    }

    @Override
    public Employee findById(long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee findByName(String name) {
        TypedQuery<Employee> theQuery = entityManager.createQuery("select e from Employee e where e.firstName=:name", Employee.class);
        theQuery.setParameter("name",name);
        Employee employee = theQuery.getSingleResult();
        return employee;
    }

    @Override
    public void save(Employee employee) {
        Employee dbEmployee = entityManager.merge(employee);
        employee.setId(dbEmployee.getId());
    }

    @Override
    public void deleteById(long id) {
        Query theQuery = entityManager.createQuery("delete from Emplotee where id=:id");
        theQuery.setParameter("id",id);
        theQuery.executeUpdate();
    }
}
