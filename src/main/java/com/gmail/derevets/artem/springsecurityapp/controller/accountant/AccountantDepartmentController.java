package com.gmail.derevets.artem.springsecurityapp.controller.accountant;

import com.gmail.derevets.artem.springsecurityapp.service.interfaces.DepartmentService;
import com.gmail.derevets.artem.springsecurityapp.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/accountant/department")
public class AccountantDepartmentController {


    private final DepartmentService departmentService;


    private final UserService userService;


    @Autowired
    public AccountantDepartmentController(DepartmentService departmentService, UserService userService) {
        this.departmentService = departmentService;
        this.userService = userService;
    }


    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView listDepartment(ModelAndView modelAndView) {
        modelAndView.addObject("listDepartment", this.departmentService.list());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("accountant/department/all");
        return modelAndView;
    }
}
