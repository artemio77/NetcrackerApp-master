package com.gmail.derevets.artem.springsecurityapp.controller.admin;

import com.gmail.derevets.artem.springsecurityapp.model.Department;
import com.gmail.derevets.artem.springsecurityapp.service.imp.UserServiceImpl;
import com.gmail.derevets.artem.springsecurityapp.service.interfaces.DepartmentService;
import com.gmail.derevets.artem.springsecurityapp.service.interfaces.JobService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/admin/department")
public class AdminDepartmentController {

    private Logger logger = Logger.getLogger(AdminDepartmentController.class.getName());
    private final DepartmentService departmentService;

    private final UserServiceImpl userService;

    private final JobService jobService;

    @Autowired
    public AdminDepartmentController(DepartmentService departmentService, UserServiceImpl userService, JobService jobService) {
        this.departmentService = departmentService;
        this.userService = userService;
        this.jobService = jobService;
    }

    @RequestMapping(value = {"/", ""})
    public ModelAndView listDepartment(ModelAndView modelAndView) {
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.addObject("listDepartment", this.departmentService.list());
        modelAndView.setViewName("admin/department/all");
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editDepartment(@PathVariable(value = "id") String id,
                                       ModelAndView modelAndView) {
        Department department = departmentService.findByDeptId(id);
        modelAndView.addObject("department", department);
        modelAndView.addObject("listDepartment", this.departmentService.list());
        modelAndView.addObject("jobList", this.jobService.getAllJob());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("admin/department/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateDepartment(
            @RequestParam("id") String id,
            @RequestParam("name") String name,
            @RequestParam("loc") String loc,
            ModelAndView modelAndView
    ) {
        Department department = new Department();
        department.setId(id);
        department.setName(name);
        department.setLoc(loc);
        departmentService.update(department);
        modelAndView.setViewName("redirect:/admin/department");
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveDepartment(
            @RequestParam("name") String name,
            @RequestParam("loc") String loc,
            ModelAndView modelAndView
    ) {
        final Department department = new Department();
        department.setName(name);
        department.setLoc(loc);
        departmentService.create(department);
        modelAndView.setViewName("redirect:/admin/department");
        return modelAndView;
    }

    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView deleteDepartment(
            @PathVariable(value = "id") final String id,
            final ModelAndView modelAndView
    ) {
        this.departmentService.removeDeptById(id);
        modelAndView.setViewName("redirect:/admin/department");
        return modelAndView;
    }
}
