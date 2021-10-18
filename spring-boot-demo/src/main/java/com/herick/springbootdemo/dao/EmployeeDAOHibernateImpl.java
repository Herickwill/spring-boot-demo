package com.herick.springbootdemo.dao;

import com.herick.springbootdemo.entity.Employee;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements  EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.createQuery("select e from Employee e", Employee.class)
                .getResultList();
    }

    @Override
    public Employee findById(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Employee.class, id);
    }

    @Override
    public Employee findByName(String name) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query query = currentSession.createQuery("select e from Employee e where e.firstName = :name");
        query.setParameter("name", name);
        Employee employee = (Employee) query.getSingleResult();
        return employee;
    }

    @Override
    public void save(Employee employee) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(employee);
    }

    @Override
    public void deleteById(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
        theQuery.setParameter("employeeId", id);
        theQuery.executeUpdate();
    }
}
