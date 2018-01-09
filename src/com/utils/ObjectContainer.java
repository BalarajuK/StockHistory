package com.utils;


import com.test.Employee;

import java.util.HashMap;
import java.util.Map;

public class ObjectContainer {
    private Map<String, Employee> employeeMap = new HashMap<>();
    private static int nextEmpId = 2;
    static private ObjectContainer instance;

    private ObjectContainer(){

    }

    public Employee getEmployee(String ename){
        Employee employee = employeeMap.get(ename);
        if (employee == null) {
            employee = new Employee();
            employeeMap.put(employee.getName(), employee);
            employee.setId(nextEmpId++);
        }
        return employee;
    }

    synchronized public static ObjectContainer getInstance(){
        if(instance == null){
            instance = new ObjectContainer();
        }
        return instance;
    }

}
