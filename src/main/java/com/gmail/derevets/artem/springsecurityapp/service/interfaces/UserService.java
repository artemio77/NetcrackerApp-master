package com.gmail.derevets.artem.springsecurityapp.service.interfaces;

import com.gmail.derevets.artem.springsecurityapp.model.User;

import java.sql.SQLException;

/**
 * Service class for {@link User}
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public interface UserService {

    void create(User user);

    User findByUsername(String username) throws SQLException;

    User getAuthenticatedUser();
}
