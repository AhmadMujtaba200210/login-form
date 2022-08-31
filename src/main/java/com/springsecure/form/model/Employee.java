package com.springsecure.form.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee  {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id;
    String f_name;
    String l_name;
    String email;
    String passcode;

    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();
}
