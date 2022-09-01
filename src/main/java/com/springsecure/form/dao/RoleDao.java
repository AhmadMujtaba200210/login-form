package com.springsecure.form.dao;

import com.springsecure.form.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleDao extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
