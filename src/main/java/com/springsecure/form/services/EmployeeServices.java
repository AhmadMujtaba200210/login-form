package com.springsecure.form.services;

import com.springsecure.form.model.Employee;

import java.util.List;

public interface EmployeeServices {
    public List<Employee> getAllEmployee();
    public Employee getEmployee(String email,String password);
    public Boolean validateEmail(String email);
    public void postEmployee(Employee emp);
    public void deleteEmployee(String email);
}
