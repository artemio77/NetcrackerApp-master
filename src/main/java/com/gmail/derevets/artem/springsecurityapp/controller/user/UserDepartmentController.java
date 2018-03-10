package com.gmail.derevets.artem.springsecurityapp.controller.user;

import com.gmail.derevets.artem.springsecurityapp.service.interfaces.DepartmentService;
import com.gmail.derevets.artem.springsecurityapp.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping(value = "/user/department")
public class UserDepartmentController {

    private final DepartmentService departmentService;

    private final UserService userService;

    @Autowired
    public UserDepartmentController(DepartmentService departmentService, UserService userService) {
        this.departmentService = departmentService;
        this.userService = userService;
    }

    @RequestMapping(value = {"/",""},method = RequestMethod.GET)
    public ModelAndView listDepartment(ModelAndView modelAndView){
        modelAndView.addObject("departmentList", this.departmentService.list());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("user/department/all");
        return modelAndView;
    }



}
