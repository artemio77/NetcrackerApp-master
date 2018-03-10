package com.gmail.derevets.artem.springsecurityapp.dao.interfaces;

import com.gmail.derevets.artem.springsecurityapp.model.Department;

import java.util.List;

public interface DepartmentDao {

    Department getDepartment(String id);

    void create(Department department);

    void update(Department department);

    void remove(String id);

    List<Department> list();

}
