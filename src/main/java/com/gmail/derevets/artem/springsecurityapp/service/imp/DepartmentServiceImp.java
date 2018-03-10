package com.gmail.derevets.artem.springsecurityapp.service.imp;


import com.gmail.derevets.artem.springsecurityapp.dao.imp.DepartmentDaoImp;
import com.gmail.derevets.artem.springsecurityapp.dao.interfaces.DepartmentDao;
import com.gmail.derevets.artem.springsecurityapp.model.Department;
import com.gmail.derevets.artem.springsecurityapp.service.interfaces.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentService ,Serializable{
    Logger logger = Logger.getLogger(DepartmentDaoImp.class.getName());
    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImp(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    @Transactional
    public void create(Department department) {
        departmentDao.create(department);
    }

    @Override
    @Transactional
    public void update(Department department) {
        departmentDao.update(department);
    }

    @Override
    public Department findByDeptId(String deptId) {
        return departmentDao.getDepartment(deptId);
    }

    @Override
    public void removeDeptByName(String id) {
        departmentDao.remove(id);
    }

    @Override
    public List<Department> list() {
        return departmentDao.list();
    }

}
