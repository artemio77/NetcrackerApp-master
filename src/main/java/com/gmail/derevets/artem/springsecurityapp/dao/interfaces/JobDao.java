package com.gmail.derevets.artem.springsecurityapp.dao.interfaces;

import com.gmail.derevets.artem.springsecurityapp.model.Job;

import java.awt.*;
import java.util.List;

public interface JobDao {
    List<Job> listJobAll();
}
