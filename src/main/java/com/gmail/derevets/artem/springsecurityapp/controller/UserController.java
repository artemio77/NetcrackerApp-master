package com.gmail.derevets.artem.springsecurityapp.controller;

import com.gmail.derevets.artem.springsecurityapp.controller.admin.AdminDepartmentController;
import com.gmail.derevets.artem.springsecurityapp.model.User;
import com.gmail.derevets.artem.springsecurityapp.service.imp.UserServiceImpl;
import com.gmail.derevets.artem.springsecurityapp.validator.UserValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


/**
 * Controller for {@link User}'s pages.
 *
 * @version 1.0
 */

@Controller
public class UserController {

    private final UserServiceImpl userService;

    private Logger logger = Logger.getLogger(AdminDepartmentController.class.getName());

    private final UserValidator userValidator;

    @Autowired
    public UserController(UserServiceImpl userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }


    @RequestMapping(value = "/login/registrForm", method = RequestMethod.POST)
    public String registration(@ModelAttribute("registrationForm") User user, BindingResult bindingResult, String error, Model model) {
        logger.info(user);
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "login";
        }
        userService.create(user);
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        model.addAttribute("registrationForm", new User());
        return "login";
    }


    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model modelAndView) {
        User user = this.userService.getAuthenticatedUser();
        modelAndView.addAttribute(
                "auth_user", user
        );
        logger.info(userService.getAuthenticatedUser());
        return "welcome";
    }


}
