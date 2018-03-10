package com.gmail.derevets.artem.springsecurityapp.controller.user;

import com.gmail.derevets.artem.springsecurityapp.service.interfaces.EmployeeService;
import com.gmail.derevets.artem.springsecurityapp.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value = "/user/employee")
@Controller
public class UserEmployeeController {
    private final EmployeeService employeeService;

    private final UserService userService;

    @Autowired
    public UserEmployeeController(EmployeeService employeeService1, UserService userService) {
        this.employeeService = employeeService1;
        this.userService = userService;
    }

    @RequestMapping(value = {"/",""}, method = RequestMethod.GET)
    public ModelAndView listEmployee(ModelAndView modelAndView) {
        modelAndView.addObject("listEmployee", this.employeeService.list());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("user/employee/all");
        return modelAndView;
    }
}
