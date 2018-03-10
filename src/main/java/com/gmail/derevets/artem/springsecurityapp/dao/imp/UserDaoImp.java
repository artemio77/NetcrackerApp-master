package com.gmail.derevets.artem.springsecurityapp.dao.imp;

import com.gmail.derevets.artem.springsecurityapp.dao.interfaces.UserDao;
import com.gmail.derevets.artem.springsecurityapp.model.Role;
import com.gmail.derevets.artem.springsecurityapp.model.User;
import com.gmail.derevets.artem.springsecurityapp.utilites.Queries;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import static com.gmail.derevets.artem.springsecurityapp.utilites.Queries.*;


@Service
public class UserDaoImp
        extends ModelDao
        implements UserDao {

    private Logger logger = Logger.getLogger(UserDaoImp.class.getName());

    /**
     * ID типа объекта в базе данных.
     */
    private final Long OBJECT_TYPE_ID = 3L;
    /**
     * ID роли клиента в базе данных.
     */
    private final Long USER_ROLE_ID = 1L;

    /**
     * ID роли адмиистратора в базе данных.
     */
    private final Long ADMIN_ROLE_ID = 2L;

    /**
     * ID роли бугалтера в базе данных.
     */
    private final Long ACCOUNTANT_ROLE_ID = 3L;
    /**
     * ID атрибута роли в базе данных.
     */
    private final Long ROLE_ATTR_ID = 11L;

    /**
     * ID атрибута пароля в базе данных.
     */
    private final Long PASSWORD_ATTR_ID = 12L;

    @Override
    public User getUser(String username) throws NullPointerException {

        User user = jdbcTemplate.query(selectByName() + "'" + username + "'", rs -> {
            User user1 = new User();
            while (rs.next()) {
                user1.setUsername(rs.getString("OBJECT_NAME"));
                if (rs.getInt("ATTR_ID") == PASSWORD_ATTR_ID) {
                    user1.setPassword(rs.getString("VALUE"));
                }
                if (rs.getInt("ATTR_ID") == ROLE_ATTR_ID) {
                    if (rs.getLong("VALUE") == ADMIN_ROLE_ID) {
                        user1.setRole("ROLE_" + Role.ADMIN.toString());
                        return user1;
                    }
                    if (rs.getInt("VALUE") == USER_ROLE_ID) {
                        user1.setRole("ROLE_" + Role.USER.toString());
                        return user1;
                    }
                    if (rs.getInt("VALUE") == ACCOUNTANT_ROLE_ID) {
                        user1.setRole("ROLE_" + Role.ACCOUNTANT.toString());
                        return user1;
                    }
                }
            }
            return user1;
        });
        if (user != null) {
            logger.info("Successful getting user " + user + " from database ");
        } else logger.error("User " + username + " not found in database");
        return user;

    }

    @Override
    public void create(User user) {
        if (user.getRole().equals(Role.ADMIN.toString())) {
            user.setRole(ADMIN_ROLE_ID.toString());
        }
        if (user.getRole().equals(Role.USER.toString())) {
            user.setRole(USER_ROLE_ID.toString());
        }
        if (user.getRole().equals(Role.ACCOUNTANT.toString())) {
            user.setRole(ACCOUNTANT_ROLE_ID.toString());
        }
        jdbcTemplate.update(insertIntoObjectQuery(),
                user.getUsername(),
                OBJECT_TYPE_ID);
        jdbcTemplate.update(Queries.createTextValueQuery(), user.getPassword(), PASSWORD_ATTR_ID,
                jdbcTemplate.queryForObject(selectIdQuery(), new Object[]{user.getUsername()}, String.class));
        jdbcTemplate.update(Queries.createNumberValueQuery(), user.getRole(), ROLE_ATTR_ID,
                jdbcTemplate.queryForObject(selectIdQuery(), new Object[]{user.getUsername()}, String.class));
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return this.getUser(auth.getName());
    }

    @Override
    public String checkGrantForUser(User user) {

        return null;
    }


}
