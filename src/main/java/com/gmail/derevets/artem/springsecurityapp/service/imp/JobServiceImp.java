package com.gmail.derevets.artem.springsecurityapp.service.imp;

import com.gmail.derevets.artem.springsecurityapp.dao.interfaces.JobDao;
import com.gmail.derevets.artem.springsecurityapp.model.Job;
import com.gmail.derevets.artem.springsecurityapp.service.interfaces.JobService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class JobServiceImp implements JobService,Serializable {

    @Autowired
    private JobDao jobDao;

    @Override
    public List<Job> getAllJob() {
        return jobDao.listJobAll();
    }
}
