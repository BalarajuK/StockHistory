package com.test;


public class EmployeeCredentials {
    private Employee employee;
    private String id;
    private String password;

    public EmployeeCredentials(Employee employee, String password) {
        this.employee = employee;
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getPassword() {
        return password;
    }
}
