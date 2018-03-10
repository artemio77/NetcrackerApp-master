package com.gmail.derevets.artem.springsecurityapp.dao.imp;

import com.gmail.derevets.artem.springsecurityapp.utilites.Queries;
import com.gmail.derevets.artem.springsecurityapp.dao.interfaces.DepartmentDao;
import com.gmail.derevets.artem.springsecurityapp.model.Department;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;

import static com.gmail.derevets.artem.springsecurityapp.utilites.Queries.*;

@Service
public class DepartmentDaoImp
        extends ModelDao
        implements DepartmentDao,Serializable {

    private Logger logger = Logger.getLogger(UserDaoImp.class.getName());

    /**
     * ID типа объекта в базе данных.
     */
    private final Long OBJECT_TYPE_ID = 1L;

    /**
     * ID атрибута локации в базе данных.
     */
    private final Long LOC_ATTR_ID = 10L;

    private Department department;

    @Autowired
    public DepartmentDaoImp(Department department) {
        this.department = department;
    }

    @Override
    @Transactional
    public Department getDepartment(String deptId) {
        if (Cache.getObjectFromCache(deptId) != null) {
            logger.info("return from cache");
            return (Department) Cache.getObjectFromCache(deptId);
        } else
            department = jdbcTemplate.query(selectByObjectId() + "'" + deptId + "'", resultSet -> {
                while (resultSet.next()) {
                    department.setName(resultSet.getString("OBJECT_NAME"));
                    department.setLoc(resultSet.getString("VALUE"));
                    department.setId(resultSet.getString("OBJECT_ID"));
                    return department;
                }
                logger.error("DEPARTMENT " + department + " NOT FOUND");
                return department;
            });
        return department;
    }

    @Override
    @Transactional
    public void create(Department department) {
        jdbcTemplate.update(Queries.insertIntoObjectQuery(),
                department.getName(),
                OBJECT_TYPE_ID);
        jdbcTemplate.update(createTextValueQuery(), department.getLoc(), LOC_ATTR_ID,
                jdbcTemplate.queryForObject(selectIdQuery(), new Object[]{department.getName()}, String.class));
    }

    @Override
    @Transactional
    public void update(Department department) {
        jdbcTemplate.update(Queries.updateObjectNameQuery(), department.getName(), department.getId(), OBJECT_TYPE_ID);
        jdbcTemplate.update(Queries.updateTextValueQuery(), department.getLoc(), department.getId(), LOC_ATTR_ID);
        Cache.removeFromCache(department.getId());
    }

    @Override
    @Transactional
    public void remove(String id) {
        jdbcTemplate.update(deleteObjectQuery(), id);
        jdbcTemplate.update(deleteParamsQuery(), id);
    }

    @Transactional
    @Override
    public List<Department> list() {
        return jdbcTemplate.query(selectObjectType(), (rs, rowNum) -> {
            Department department1 = new Department();
            department1.setName(rs.getString("OBJECT_NAME"));
            department1.setLoc(rs.getString("VALUE"));
            department1.setId(rs.getString("OBJECT_ID"));
            Cache.addObjectToCache(department1);
            return department1;
        });
    }


}
