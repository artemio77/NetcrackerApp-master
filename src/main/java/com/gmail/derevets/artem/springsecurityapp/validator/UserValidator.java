package com.gmail.derevets.artem.springsecurityapp.validator;

import com.gmail.derevets.artem.springsecurityapp.model.User;
import com.gmail.derevets.artem.springsecurityapp.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.sql.SQLException;

/**
 * Validator for {@link User} class,
 * implements {@link Validator} interface.
 * @version 1.0
 */

@Component
@PropertySource("classpath:validation.properties")
public class UserValidator {

    @Autowired
    private UserService userService;

    @Autowired
    Environment environment;


    public void validate(User user, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", environment.getProperty("Required"));
        if (user.getUsername().length() < 3 || user.getUsername().length() > 32) {
            errors.rejectValue("username", environment.getProperty("Size.userForm.username"));
        }

        try {
            if (userService.findByUsername(user.getUsername()).getUsername() !=null) {
                errors.rejectValue("username", environment.getProperty("Duplicate.userForm.username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", environment.getProperty("Required"));
        if (user.getPassword().length() < 3 || user.getPassword().length() > 32) {
            errors.rejectValue("password",environment.getProperty("Size.userForm.password"));
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", environment.getProperty("Different.userForm.password"));
        }

    }
}
