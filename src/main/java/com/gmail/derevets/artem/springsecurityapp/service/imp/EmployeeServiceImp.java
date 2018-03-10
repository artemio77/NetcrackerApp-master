package com.gmail.derevets.artem.springsecurityapp.service.imp;

import com.gmail.derevets.artem.springsecurityapp.dao.imp.Cache;
import com.gmail.derevets.artem.springsecurityapp.dao.interfaces.EmployeeDao;
import com.gmail.derevets.artem.springsecurityapp.model.Employee;
import com.gmail.derevets.artem.springsecurityapp.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService,Serializable {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImp(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    @Transactional
    public void create(Employee employee) {
        employeeDao.create(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);
        Cache.removeFromCache(employee.getId());
    }

    @Override
    public Employee findById(String id) {
        if (Cache.getObjectFromCache(id) != null) {
            return (Employee) Cache.getObjectFromCache(id);
        } else
            return employeeDao.getEmployee(id);
    }

    @Override
    public void removeById(String id) {
        employeeDao.remove(id);
    }

    @Override
    public List<Employee> list() {
        return employeeDao.getAllEmployees();
    }


}
