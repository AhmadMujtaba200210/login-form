package com.springsecure.form.services;

import com.springsecure.form.model.Employee;
import com.springsecure.form.model.Role;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface EmployeeServices {
    public List<Employee> getAllEmployee();
    public Employee getEmployee(String email,String password);
    public Boolean validateEmail(String email);
    public void postEmployee(Employee emp);
    public void deleteEmployee(String email);
    public void updateEmployee(String email,Employee emp) throws Exception;
    public Role addRole(Role role);
    public String deleteRole(String name) throws RoleNotFoundException;// @PathVariable
    public Employee assignRole(String email,String role);// @PathVariable, @PathVariable
    public Role updateRole(String role,Role roleClass) throws RoleNotFoundException; // @PathVariable, @ResponseBody
    public List<Role> getAllRole();
    public Role getRoleByName(String role); // @PathVariable
}
