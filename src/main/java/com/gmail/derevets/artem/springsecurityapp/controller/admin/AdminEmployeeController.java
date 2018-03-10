package com.gmail.derevets.artem.springsecurityapp.controller.admin;

import com.gmail.derevets.artem.springsecurityapp.model.Department;
import com.gmail.derevets.artem.springsecurityapp.model.Employee;
import com.gmail.derevets.artem.springsecurityapp.model.Job;
import com.gmail.derevets.artem.springsecurityapp.service.interfaces.DepartmentService;
import com.gmail.derevets.artem.springsecurityapp.service.interfaces.EmployeeService;
import com.gmail.derevets.artem.springsecurityapp.service.interfaces.JobService;
import com.gmail.derevets.artem.springsecurityapp.service.interfaces.UserService;
import com.gmail.derevets.artem.springsecurityapp.utilites.Utilises;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/employee")
public class AdminEmployeeController {

    private final EmployeeService employeeService;

    private final JobService jobService;

    private final UserService userService;

    private final DepartmentService departmentService;

    private Logger logger = Logger.getLogger(AdminEmployeeController.class.getName());

    @Autowired
    public AdminEmployeeController(EmployeeService employeeService, JobService jobService, DepartmentService departmentService, UserService userService) {
        this.employeeService = employeeService;
        this.jobService = jobService;
        this.departmentService = departmentService;
        this.userService = userService;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editEmployee(@PathVariable(value = "id") String id,
                                     ModelAndView modelAndView) {
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.addObject("listJob", this.jobService.getAllJob());
        modelAndView.addObject("listDepartment", this.departmentService.list());
        modelAndView.addObject("employee", this.employeeService.findById(id));
        modelAndView.setViewName("admin/employee/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateEmployee(
            @RequestParam("id") String id,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("job") String job,
            @RequestParam("salary") Long salary,
            @RequestParam("department") String department_name,
            ModelAndView modelAndView
    ) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setJob(job);
        employee.setSalary(salary);
        employee.setDepartmentName(department_name);
        logger.info(employee);
        employeeService.update(employee);
        modelAndView.setViewName("redirect:/admin/employee");
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveEmployee(@RequestParam("name") String name,
                                     @RequestParam("surname") String surname,
                                     @RequestParam("job") String job,
                                     @RequestParam("salary") Long salary,
                                     @RequestParam("department") String department_name,
                                     ModelAndView modelAndView) throws ParseException {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setJob(job);
        employee.setHiredate(Utilises.timeStamp());
        employee.setSalary(salary);
        employee.setDepartmentName(department_name);
        employeeService.create(employee);
        modelAndView.setViewName("redirect:/admin/employee");
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(@PathVariable("id") String id, final ModelAndView modelAndView) {
        employeeService.removeById(id);
        modelAndView.setViewName("redirect:/admin/employee");
        return modelAndView;
    }



    @RequestMapping(value = {"/", ""})
    public ModelAndView listEmployee(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.addObject("jobList", this.jobService.getAllJob());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.addObject("listDepartment", this.departmentService.list());
        modelAndView.addObject("listEmployee", this.employeeService.list());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("admin/employee/all");
        return modelAndView;
    }
}
