package com.springsecure.form.services;

import com.springsecure.form.dao.EmployeeDao;
import com.springsecure.form.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor  // I have customarily created the EmployeeDetailsServices and EmployeeDetails Interface and implemented here ....
public class EmployeeServicesImpl implements EmployeeServices, EmployeeDetailsServices {


    /**
     I have created the EmployeeDetails and EmployeeDetailsServices Interfaces customarily and then implemented the
     EmployeeDetails Interface in Employee Class in Model Package and also used the EmployeeDetails as return type
     in EmployeeDetailsServices method.
    **/
    @Override
    public EmployeeDetails loadUserByEmpname(String username) throws UsernameNotFoundException {
        Employee emp=employeeDao.findByEmail(username);
        if(emp==null){
            throw new UsernameNotFoundException("User doesnot exist with this email");
        }
        return new Employee(emp.getEmail(),emp.getPasscode());
    }

    /**
     * It is currently returning null... overriding required
     * @param username the username identifying the user whose data is required.
     * @return null
     * @throws UsernameNotFoundException if username is null
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    private final EmployeeDao employeeDao;
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
        employeeDao.save(emp);
    }

    public void deleteEmployee(String email) {
        Employee emp=employeeDao.findByEmail(email);
        employeeDao.delete(emp);
    }



}
