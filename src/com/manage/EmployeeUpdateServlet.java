package com.manage;

import com.login.LoginDao;
import com.test.Employee;
import com.test.EmployeeUtils;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

public class EmployeeUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        Employee employee = new EmployeeUtils().validateRequest(req);
        if (employee != null) {
            String ename = req.getParameter("name");
            if (ename == null) {
                resp.sendRedirect("UpdateForm.html");
                req.getRequestDispatcher("link.html").include(req, resp);

            } else {
//                req.getRequestDispatcher("link.html").include(req, resp);
                List<Employee> employees = LoginDao.getEmployees(new Configuration().configure().buildSessionFactory(), req.getParameter("name"));

                String name = req.getParameter("name");
                String address = req.getParameter("address");
                String sal = req.getParameter("salary");
                String password = req.getParameter("password");

                Employee employee1 = null;
                if (!employees.isEmpty()) {
                    System.out.println("Found given employee");
                    employee1 = employees.get(0);
                } else {
                    System.out.println("Does not found given employee");
                    employee1 = new Employee();
                    employee1.setId(new Random(100).nextInt());
                }


                employee1.setName(name);
                employee.setPassword(password);
                employee1.setAddress(address);
                employee1.setSalary(Double.valueOf(sal));


                LoginDao.createEmployees(new Configuration().configure().buildSessionFactory(), employee1);

                req.getRequestDispatcher("link.html").include(req, resp);


            }
            Cookie ck2 = new Cookie("name", employee.getName() + "," + employee.getPassword());
            resp.addCookie(ck2);
        } else {

            out.print("Please login first");
            req.getRequestDispatcher("login.html").include(req, resp);

        }
    }
}
