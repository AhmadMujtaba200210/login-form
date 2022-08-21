package com.springsecure.form.dao;

import com.springsecure.form.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee,Long> {
    Employee findByEmail(String email);
}
