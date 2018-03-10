package com.gmail.derevets.artem.springsecurityapp.model;


import java.io.Serializable;

public class Employee extends Model implements Serializable {

    private String name;

    private String surname;

    private String job;

    private String hiredate ;

    private Long salary;

    private String departmentName;

    public Employee() {
    }

    public Employee(String id, String name, String surname, String job, String hiredate, Long salary, String departmentName) {
        super.id = id;
        this.name = name;
        this.surname = surname;
        this.job = job;
        this.hiredate = hiredate;
        this.salary = salary;
        this.departmentName = departmentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
            this.hiredate = hiredate;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", job='" + job + '\'' +
                ", hiredate='" + hiredate + '\'' +
                ", salary=" + salary +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
