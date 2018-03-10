package com.gmail.derevets.artem.springsecurityapp.service.imp;

import com.gmail.derevets.artem.springsecurityapp.dao.imp.UserDaoImp;
import com.gmail.derevets.artem.springsecurityapp.model.User;
import com.gmail.derevets.artem.springsecurityapp.service.interfaces.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.io.Serializable;
import java.util.Arrays;


/**
 * Implementation of {@link UserService} interface.
 *
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService, UserDetailsService,Serializable {

    @Autowired
    private UserDaoImp userDao;

    private Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.getUser(username);
    }

    @Override
    public User getAuthenticatedUser() {
        return userDao.getAuthenticatedUser();
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User userInfo = userDao.getUser(username);
        GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(userInfo.getUsername(),
                userInfo.getPassword(), Arrays.asList(authority));
        logger.info(userDetails);
        if (userDetails == null) throw new UsernameNotFoundException("USER" + username + "NOT FOUND");
        {
        }

        return userDetails;
    }

}


