package com.springsecure.form.model;


public class Employee {
    int id;
    String f_name;
    String l_name;
    String email;
    String passcode;

    public Employee(String f_name, String l_name, String email, String passcode) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.passcode = passcode;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", f_name='" + f_name + '\'' +
                ", l_name='" + l_name + '\'' +
                ", email='" + email + '\'' +
                ", passcode='" + passcode + '\'' +
                '}';
    }
}
