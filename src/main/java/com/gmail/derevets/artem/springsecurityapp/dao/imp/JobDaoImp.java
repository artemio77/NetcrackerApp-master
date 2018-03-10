package com.gmail.derevets.artem.springsecurityapp.dao.imp;

import com.gmail.derevets.artem.springsecurityapp.dao.interfaces.JobDao;
import com.gmail.derevets.artem.springsecurityapp.model.Job;
import com.gmail.derevets.artem.springsecurityapp.utilites.Queries;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImp
        extends ModelDao
        implements JobDao {

    private final Long OBJECT_TYPE_ID = 4L;



    @Override
    @Transactional
    public List<Job> listJobAll() {
        List<Job> list = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(Queries.selectByObjectType(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setLong(1, OBJECT_TYPE_ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Job job = new Job();
                job.setName(resultSet.getString("VALUE"));
                list.add(job);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
