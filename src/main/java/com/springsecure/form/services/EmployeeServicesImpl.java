package com.springsecure.form.services;

import com.springsecure.form.dao.EmployeeDao;
import com.springsecure.form.dao.RoleDao;
import com.springsecure.form.model.Employee;
import com.springsecure.form.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor  // I have customarily created the EmployeeDetailsServices and EmployeeDetails Interface and implemented here ....
public class EmployeeServicesImpl implements EmployeeServices,UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmployeeDao employeeDao;
    private final RoleDao roleDao;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee emp=employeeDao.findByEmail(username);
        if(emp==null) throw new UsernameNotFoundException("User not found!");
        String email=emp.getEmail();
        String passcode=emp.getPasscode();
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        emp.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(email,passcode,authorities);
    }
    public List<Employee> getAllEmployee(){
        return employeeDao.findAll();
    }

    public Employee getEmployee(String email,String password){
        return employeeDao.findByEmail(email);
    }

    public Boolean validateEmail(String email){
        return employeeDao.findByEmail(email) == null;
    }
    public Boolean validateRole(String roleName){
        return roleDao.findByName(roleName)==null;
    }

    public void postEmployee(Employee emp){
       emp.setPasscode(bCryptPasswordEncoder.encode(emp.getPasscode()));
        employeeDao.save(emp);
    }

    public void deleteEmployee(String email) {
        employeeDao.delete(employeeDao.findByEmail(email));
    }
    public void updateEmployee(String email,Employee tempEmp) throws Exception{
        if(validateEmail(tempEmp.getEmail())) throw new UsernameNotFoundException("Email not found!");
        Employee emp=employeeDao.findByEmail(email);
        if(emp.getF_name()!=tempEmp.getF_name()) throw new Exception("You cannot change these terms!");
        employeeDao.save(tempEmp);
    }


    public Role addRole(Role role) {
        roleDao.save(role);
        return role;
    }


    public String deleteRole(String name) throws RoleNotFoundException{
        if(validateRole(name)) throw new RoleNotFoundException("Role is not available!");
        roleDao.delete(roleDao.findByName(name));
        return "Role has been deleted!";
    }


    public Employee assignRole(String email, String roleName) throws Exception {
        if(!validateEmail(email)){
           Role role=roleDao.findByName(roleName);
           Employee emp=employeeDao.findByEmail(email);
           emp.getRoles().add(role);
           updateEmployee(emp.getEmail(),emp);
           return emp;
        }
        throw new UnsupportedOperationException("Check fields");
    }


    public Role updateRole(String role, Role roleClass) throws RoleNotFoundException{
        if(validateRole(role)) throw new RoleNotFoundException("Role is not available!");
        return roleDao.save(roleClass);
    }


    public List<Role> getAllRole() {
        return roleDao.findAll();
    }


    public Role getRoleByName(String role) {
        return roleDao.findByName(role);
    }



}
