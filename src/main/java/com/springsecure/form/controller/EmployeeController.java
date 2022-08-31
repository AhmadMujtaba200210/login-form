package com.springsecure.form.controller;

import com.springsecure.form.model.Employee;
import com.springsecure.form.model.Role;
import com.springsecure.form.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@RestController
@RequestMapping
public class EmployeeController {
    @Autowired
    private EmployeeServices employeeServices;

    @GetMapping("/getEmployee")
    public ResponseEntity<List<Employee>> getEmployee(){
       return ResponseEntity.ok().body(employeeServices.getAllEmployee());
    }

    @GetMapping("/emailGet")
    public Employee getEmployeebyEmail(@RequestBody Employee user){
        return employeeServices.getEmployee(user.getEmail(),user.getPasscode());
    }

    @PostMapping("/postEmployee")
    public ResponseEntity<HttpRequest> postByEmail(@RequestBody Employee emp){

        if(!employeeServices.validateEmail(emp.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        employeeServices.postEmployee(emp);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/updateEmployee/{email}")
    public ResponseEntity<HttpRequest> updateEmployee(@PathVariable String email,@RequestBody Employee emp) throws Exception {

        employeeServices.updateEmployee(email,emp);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteEmployee/{email}")
    public ResponseEntity<HttpRequest> deleteEmployee(@PathVariable String email){

        employeeServices.deleteEmployee(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addRole")
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        Role tempRole=this.employeeServices.addRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(tempRole);
    }

    @GetMapping("/getRoleName/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name){
        Role tempRole=this.employeeServices.getRoleByName(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(tempRole);
    }

    @GetMapping("/getRole")
    public ResponseEntity<List<Role>> getRoleByName(){
       List<Role> tempRole=this.employeeServices.getAllRole();
        return ResponseEntity.status(HttpStatus.CREATED).body(tempRole);
    }

    @DeleteMapping("/DeleteRole")
    public ResponseEntity<String> deleteRole(@PathVariable String name) throws RoleNotFoundException {
        String msg=this.employeeServices.deleteRole(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @PutMapping("/updateRole")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name,@RequestBody Role role) throws RoleNotFoundException {
        Role tempRole=this.employeeServices.updateRole(name,role);
        return ResponseEntity.status(HttpStatus.CREATED).body(tempRole);
    }
    @PutMapping("/addRoleToEmployee")
    public ResponseEntity<Employee> addRoleToEmployee(@PathVariable String email,@PathVariable String roleName) throws RoleNotFoundException {
        Employee emp=this.employeeServices.assignRole(email,roleName);
        return ResponseEntity.status(HttpStatus.CREATED).body(emp);
    }

}
