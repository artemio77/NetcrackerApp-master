package com.gmail.derevets.artem.springsecurityapp.service.interfaces;

import com.gmail.derevets.artem.springsecurityapp.model.Department;

import java.util.List;

public interface DepartmentService {

    void create(Department department);

    void update(Department department);

    Department findByDeptId(String deptId);

    void removeDeptById(String deptId);

    List<Department> list();


}
