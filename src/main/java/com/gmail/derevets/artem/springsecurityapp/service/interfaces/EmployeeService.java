package com.gmail.derevets.artem.springsecurityapp.service.interfaces;

import com.gmail.derevets.artem.springsecurityapp.model.Employee;

import java.util.List;

public interface EmployeeService {

    void create(Employee employee);

    void update(Employee employee);

    Employee findById(String id);

    void removeById(String id);

    List<Employee> list();

}
