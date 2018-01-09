package com.login;

import com.test.Employee;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LoginDao {
    public static boolean validate(String name, String pass) {
        boolean status = false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");

            PreparedStatement ps = con.prepareStatement(
                    "select * from userreg where name=? and pass=?");
            ps.setString(1, name);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            status = rs.next();

        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static Employee validate(SessionFactory sessionFactory, String name, String pass) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Employee validUser = null;
        try {
            //validUser = getEmployeeByHQL(name, pass, session, validUser);
            validUser = getEmployeeByHCQL(name, pass, session, validUser);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
        return validUser;
    }

    public static List<Employee> getEmployees(SessionFactory sessionFactory, String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            return getEmploysByHCQL(name, session);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
        return Collections.emptyList();
    }


    public static List<Employee> createEmployees(SessionFactory sessionFactory, Employee emp) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.save(emp);
            System.out.println("Employee create: " + emp.getName());

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
        return Collections.emptyList();
    }

    private static Employee getEmployeeByHCQL(String name, String pass, Session session, Employee validUser) {
        Criteria c = session.createCriteria(Employee.class);
        c.add(Restrictions.eq("name", name));
        c.add(Restrictions.eq("password", pass));
        List list = c.list();
        validUser = !list.isEmpty() ? (Employee) list.get(0) : null;
        return validUser;
    }

    private static List<Employee> getEmploysByHCQL(String name, Session session) {
        Criteria c = session.createCriteria(Employee.class);
        c.add(Restrictions.like("name", name));
        List list = c.list();
        List<Employee> employees = new ArrayList<>();
        for (Object emp : list) {
            employees.add((Employee) emp);
        }
        return employees;
    }

    private static Employee getEmployeeByHQL(String name, String pass, Session session, Employee validUser) {
        String hql = "FROM com.test.Employee E WHERE E.name = :ename AND E.password = :epass";
        Query query = session.createQuery(hql);
        query.setParameter("ename", name);
        query.setParameter("epass", pass);
        List list = query.list();
        validUser = !list.isEmpty() ? (Employee) list.get(0) : null;
        return validUser;
    }
}
