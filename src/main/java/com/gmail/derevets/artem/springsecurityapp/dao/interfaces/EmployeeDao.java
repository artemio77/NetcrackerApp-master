package com.gmail.derevets.artem.springsecurityapp.dao.interfaces;

import com.gmail.derevets.artem.springsecurityapp.model.Employee;

import java.util.List;

public interface EmployeeDao {
    Employee getEmployee(String id);

    void create(Employee employee);

    void update(Employee employee);

    void remove(String id);

    List<Employee> getAllEmployees();
}
