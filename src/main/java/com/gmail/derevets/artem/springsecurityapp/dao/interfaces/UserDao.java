package com.gmail.derevets.artem.springsecurityapp.dao.interfaces;

import com.gmail.derevets.artem.springsecurityapp.model.User;

public interface UserDao {
    User getUser(String username);

    void create(User user);

    User getAuthenticatedUser();

    String checkGrantForUser(User user);

}
