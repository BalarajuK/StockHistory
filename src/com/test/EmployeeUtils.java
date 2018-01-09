package com.test;


import com.login.LoginDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class EmployeeUtils {

    public void updateEmployee(Employee employee, String ename, String address, String salary, SessionFactory sessionFactory) {
        employee.setName(ename);
        if(address!=null && !address.isEmpty()){
            employee.setAddress(address);
        }
        if(salary!=null && !salary.isEmpty()){
            employee.setSalary(Double.valueOf(salary));
        }

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.saveOrUpdate(employee);

        session.getTransaction().commit();
        session.close();
    }

    public void updateEmployee(EmployeeCredentials employeeCredentials, SessionFactory sessionFactory) {

        Employee employee = employeeCredentials.getEmployee();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.saveOrUpdate(employee);

        session.getTransaction().commit();
        session.close();
    }

    public void createUpdatePage(Employee employee, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();
        resp.setIntHeader("Refresh", 5);

        StringBuilder page = new StringBuilder();
        page.append("<html><body>").append("<title>Employee Info</title>").append("<left>")
                .append("<form name=\"Form2\"")
                .append("action=\"http://localhost:8080/test/employee\">")
                .append("<B>Employee Name :</B>").append("<input type=textbox name=\"name\" size=12 value=\"")
                .append(employee.getName() != null ? employee.getName() : "").append("\">")
                .append("<BR><B>Employee Address :</B>").append("<input type=textbox name=\"address\" size=12 value=\"")
                .append(employee.getAddress() != null ? employee.getAddress() : "").append("\">")
                .append("<BR><B>Employee Salary :</B>").append("<input type=textbox name=\"salary\" size=12 value=\"")
                .append(employee.getSalary()).append("\">")
                .append("<BR><P><input type=submit value=\"Update\" name=\"update\">")
                .append("<BR><P><input type=submit value=\"Search\" name=\"Search\\>")
                .append("</form></body> </html>");
        writer.print(page.toString());
    }

    public void createSearchPage(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();
        resp.setIntHeader("Refresh", 5);

        StringBuilder page = new StringBuilder();
        page.append("<html><body>").append("<title>Search Employee</title>").append("<left>")
                .append("<form name=\"Form2\"")
                .append("action=\"http://localhost:8080/employeeSearch\">")
                .append("<B>Employee Name :</B>").append("<input type=textbox name=\"name\" size=12 value=\"\"")
                .append("<BR><P><input type=submit value=\"Search\" name=\"Search\\>")
                .append("</form></body> </html>");
        writer.print(page.toString());
    }


    public void createUpdatePage(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();
        resp.setIntHeader("Refresh", 5);

        StringBuilder page = new StringBuilder();
        page.append("<html><body>").append("<title>Search Employee</title>").append("<left>")
                .append("<form name=\"Form2\"")
                .append("action=\"http://localhost:8080/employeeSearch\">")
                .append("<B>Employee Name :</B>").append("<input type=textbox name=\"name\" size=12 value=\"\"")
                .append("<BR><P><input type=submit value=\"Search\" name=\"Search\\>")
                .append("</form></body> </html>");
        writer.print(page.toString());
    }

    public Employee validateRequest(HttpServletRequest req) {
        Employee employee = null;
        Cookie ck[] = req.getCookies();
        if (ck != null) {
            Cookie cookie = null;
            for (Cookie cookie1 : ck) {
                if (cookie1.getName().equals("name")) {
                    cookie = cookie1;
                }

            }
            if (cookie != null) {
                String[] userDetails = cookie.getValue().split(",");
                if (userDetails.length == 2) {
                    employee = LoginDao.validate(new Configuration().configure().buildSessionFactory(), userDetails[0], userDetails[1]);
                }
            }
        }
        return employee;
    }


}
