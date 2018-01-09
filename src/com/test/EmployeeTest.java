package com.test;

import org.hibernate.cfg.Configuration;

/**
 * Created by bkadukun on 8/26/2017.
 */
public class EmployeeTest {

    public static void main(String[] args) {
        Employee employee = new Employee();
        //new EmployeeUtils().updateEmployee(employee, "Bala", "Miyapur", "1200", new Configuration().configure().buildSessionFactory());
        //new EmployeeUtils().updateEmployee(employee, "Balaraju", "Miyapur", "20000", new Configuration().configure().buildSessionFactory());
        //new EmployeeUtils().updateEmployee(employee, "Bala123", "Hyderabad", "40000", new Configuration().configure().buildSessionFactory());
        new EmployeeUtils().updateEmployee(employee, "Bala123", "Hyderabad", "40000", new Configuration().configure().buildSessionFactory());

    }



}
