package com.springsecure.form.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO.SEQUENCE)
    int id;
    String name;
    String Description;
}
