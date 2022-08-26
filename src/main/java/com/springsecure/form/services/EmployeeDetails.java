package com.springsecure.form.services;

import java.io.Serializable;

public interface EmployeeDetails extends Serializable {

    boolean isAccountNonExpired();


    boolean isAccountNonLocked();


    boolean isCredentialsNonExpired();


    boolean isEnabled();

}
