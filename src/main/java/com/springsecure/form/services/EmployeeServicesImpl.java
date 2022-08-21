package com.springsecure.form.services;

import com.springsecure.form.dao.EmployeeDao;
import com.springsecure.form.model.Employee;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServicesImpl implements EmployeeServices{
    private final EmployeeDao employeeDao;
    private final PasswordEncoder passwordEncoder;
    public List<Employee> getAllEmployee(){
        return employeeDao.findAll();
    }

    public Employee getEmployee(String email,String password){
        return employeeDao.findByEmail(email);
    }

    public Boolean validateEmail(String email){
        return employeeDao.findByEmail(email) == null;
    }

    public void postEmployee(Employee emp){
        emp.setEmail(passwordEncoder.encode(emp.getEmail()));
        employeeDao.save(emp);
    }

    public void deleteEmployee(String email) {
        Employee emp=employeeDao.findByEmail(email);
        employeeDao.delete(emp);
    }
}
