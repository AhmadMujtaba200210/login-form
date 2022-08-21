package com.springsecure.form.controller;

import com.springsecure.form.model.Employee;
import com.springsecure.form.services.EmployeeServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeServicesImpl employeeServicesImpl;

    @GetMapping("/getEmployee")
    public ResponseEntity<List<Employee>> getEmployee(){
       return ResponseEntity.ok().body(employeeServicesImpl.getAllEmployee());
    }

    @GetMapping("/emailGet")
    public Employee getEmployeebyEmail(@RequestBody User user){
        return employeeServicesImpl.getEmployee(user.email,user.password);
    }

    @PostMapping("/postEmployee")
    public ResponseEntity<HttpRequest> postByEmail(@RequestBody Employee emp){

        if(!employeeServicesImpl.validateEmail(emp.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        employeeServicesImpl.postEmployee(emp);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<HttpRequest> updateEmployee(@RequestBody Employee emp,@PathVariable User user){
        if (!employeeServicesImpl.validateEmail(user.email)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        employeeServicesImpl.postEmployee(emp);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteEmployee")
    public ResponseEntity<HttpRequest> deleteEmployee(@RequestBody User user){
        if (!employeeServicesImpl.validateEmail(user.email)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        employeeServicesImpl.deleteEmployee(user.email);
        return ResponseEntity.ok().build();
    }

     class User{
      String email;
      String password;
    }
}
