package com.springsecure.form.dao;

import com.springsecure.form.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Long> {
    Role findByRoleName(String name);
}
