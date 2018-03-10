package com.gmail.derevets.artem.springsecurityapp.controller.accountant;

import com.gmail.derevets.artem.springsecurityapp.model.Employee;
import com.gmail.derevets.artem.springsecurityapp.service.interfaces.DepartmentService;
import com.gmail.derevets.artem.springsecurityapp.service.interfaces.EmployeeService;
import com.gmail.derevets.artem.springsecurityapp.service.interfaces.JobService;
import com.gmail.derevets.artem.springsecurityapp.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/accountant/employee")
public class AccountantEmployeeController {

    private final DepartmentService departmentService;

    private final JobService jobService;

    private final EmployeeService employeeService;

    private final UserService userService;

    @Autowired
    public AccountantEmployeeController(EmployeeService employeeService, DepartmentService departmentService, JobService jobService, UserService userService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.jobService = jobService;
        this.userService = userService;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView listEmployee(ModelAndView modelAndView) {
        modelAndView.addObject("listEmployee", this.employeeService.list());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("accountant/employee/all");
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editEmployee(@PathVariable(value = "id") String id,
                                     ModelAndView modelAndView) {
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.addObject("listJob", this.jobService.getAllJob());
        modelAndView.addObject("listDepartment", this.departmentService.list());
        modelAndView.addObject("employee", this.employeeService.findById(id));
        modelAndView.setViewName("accountant/employee/edit");
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
        employeeService.update(employee);
        modelAndView.setViewName("redirect:/accountant/employee");
        return modelAndView;
    }

}
