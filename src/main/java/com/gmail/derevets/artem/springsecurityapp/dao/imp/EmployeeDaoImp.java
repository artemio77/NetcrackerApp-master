package com.gmail.derevets.artem.springsecurityapp.dao.imp;

import com.gmail.derevets.artem.springsecurityapp.dao.interfaces.EmployeeDao;
import com.gmail.derevets.artem.springsecurityapp.model.Employee;
import com.gmail.derevets.artem.springsecurityapp.utilites.Queries;
import com.gmail.derevets.artem.springsecurityapp.utilites.Utilises;
import jdk.nashorn.internal.scripts.JO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.gmail.derevets.artem.springsecurityapp.utilites.Queries.selectIdQuery;

@Service
public class EmployeeDaoImp
        extends ModelDao
        implements EmployeeDao,Serializable {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Logger logger = Logger.getLogger(EmployeeDaoImp.class.getName());

    private final Long OBJECT_TYPE_ID = 2L;

    private final Long SURNAME_ATTR_ID = 2L;

    private final Long JOB_ATTR_ID = 3L;

    private final Long HIREDATE_ATTR_ID = 4L;

    private final Long SALARY_ATTR_ID = 5L;

    private final Long DEPARTMENT_NAME_ATTR_ID = 6L;

    private Employee employee;

    @Autowired
    public EmployeeDaoImp(Employee employee) {
        this.employee = employee;
    }

    @Override
    @Transactional
    public Employee getEmployee(String id) {
        employee = jdbcTemplate.query(Queries.selectByObjectId() + id, resultSet -> {
            if (resultSet.next()) {
                employee.setId(resultSet.getString("OBJECT_ID"));
                employee.setName(resultSet.getString("OBJECT_NAME"));
                if (resultSet.getLong("ATTR_ID") == SURNAME_ATTR_ID) {
                    employee.setSurname(resultSet.getString("VALUE"));
                }
                if (resultSet.getLong("ATTR_ID") == JOB_ATTR_ID) {
                    employee.setJob(resultSet.getString("VALUE"));
                }
                if (resultSet.getLong("ATTR_ID") == HIREDATE_ATTR_ID) {
                    employee.setHiredate(resultSet.getString("VALUE"));
                }
                if (resultSet.getLong("ATTR_ID") == SALARY_ATTR_ID) {
                    employee.setSalary(resultSet.getLong("VALUE"));
                }
                if (resultSet.getLong("ATTR_ID") == DEPARTMENT_NAME_ATTR_ID) {
                    employee.setDepartmentName(resultSet.getString("VALUE"));
                }
                return employee;
            }
            return employee;
        });
        return employee;
    }

    @Override
    public void create(Employee employee) {
        jdbcTemplate.update(Queries.insertIntoObjectQuery(), employee.getName(), OBJECT_TYPE_ID);
        jdbcTemplate.update(Queries.createTextValueQuery(), employee.getSurname(),
                SURNAME_ATTR_ID, jdbcTemplate.queryForObject(selectIdQuery(), new Object[]{employee.getName()}, String.class));
        jdbcTemplate.update(Queries.createTextValueQuery(), employee.getJob(), JOB_ATTR_ID,
                jdbcTemplate.queryForObject(selectIdQuery(), new Object[]{employee.getName()}, String.class));
        jdbcTemplate.update(Queries.createDateValueParamsQuery(), employee.getHiredate(), HIREDATE_ATTR_ID,
                jdbcTemplate.queryForObject(selectIdQuery(), new Object[]{employee.getName()}, String.class));
        jdbcTemplate.update(Queries.createNumberValueQuery(), employee.getSalary(), SALARY_ATTR_ID,
                jdbcTemplate.queryForObject(selectIdQuery(), new Object[]{employee.getName()}, String.class));
        jdbcTemplate.update(Queries.createTextValueQuery(), employee.getDepartmentName(), DEPARTMENT_NAME_ATTR_ID,
                jdbcTemplate.queryForObject(selectIdQuery(), new Object[]{employee.getName()}, String.class));
    }

    @Override
    public void update(Employee employee) {
        jdbcTemplate.update(Queries.updateObjectNameQuery(), employee.getName(), employee.getId(), OBJECT_TYPE_ID);
        jdbcTemplate.update(Queries.updateTextValueQuery(), employee.getSurname(), employee.getId(), SURNAME_ATTR_ID);
        jdbcTemplate.update(Queries.updateTextValueQuery(), employee.getJob(), employee.getId(), JOB_ATTR_ID);
        jdbcTemplate.update(Queries.updateNumberValueQuery(), employee.getSalary(), employee.getId(), SALARY_ATTR_ID);
        jdbcTemplate.update(Queries.updateTextValueQuery(), employee.getDepartmentName(), employee.getId(), DEPARTMENT_NAME_ATTR_ID);
    }

    @Override
    @Transactional
    public void remove(String id) {
        jdbcTemplate.update(Queries.deleteObjectQuery(), id);
        jdbcTemplate.update(Queries.deleteParamsQuery(), id);
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(Queries.selectByObjectType(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setLong(1, OBJECT_TYPE_ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getString("OBJECT_ID"));
                employee.setName(resultSet.getString("OBJECT_NAME"));
                if (resultSet.getLong("ATTR_ID") == SURNAME_ATTR_ID) {
                    employee.setSurname(resultSet.getString("VALUE"));
                    resultSet.next();
                }
                if (resultSet.getLong("ATTR_ID") == JOB_ATTR_ID) {
                    employee.setJob(resultSet.getString("VALUE"));
                    resultSet.next();
                }
                if (resultSet.getLong("ATTR_ID") == HIREDATE_ATTR_ID) {
                    employee.setHiredate(resultSet.getString("VALUE"));
                    resultSet.next();
                }
                if (resultSet.getLong("ATTR_ID") == SALARY_ATTR_ID) {
                    employee.setSalary(resultSet.getLong("VALUE"));
                    resultSet.next();
                }
                if (resultSet.getLong("ATTR_ID") == DEPARTMENT_NAME_ATTR_ID) {
                    employee.setDepartmentName(resultSet.getString("VALUE"));
                }
                list.add(employee);
                Cache.addObjectToCache(employee);
            }
            return list;
        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }
}
