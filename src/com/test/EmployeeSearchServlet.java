package com.test;


import com.login.LoginDao;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class EmployeeSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        Employee employee = new EmployeeUtils().validateRequest(req);
        if (employee != null) {
            String ename = req.getParameter("name");
            if (ename == null) {
                new EmployeeUtils().createSearchPage(resp);

            } else {
                req.getRequestDispatcher("link.html").include(req, resp);
                List<Employee> employees = LoginDao.getEmployees(new Configuration().configure().buildSessionFactory(), req.getParameter("name"));
                if (!employees.isEmpty()) {
                    out.print("Found given person");
                } else {
                    out.print("Does not found given person");
                }

            }
            Cookie ck2 = new Cookie("name", employee.getName() + "," + employee.getPassword());
            resp.addCookie(ck2);
        } else {

            out.print("Please login first");
            req.getRequestDispatcher("login.html").include(req, resp);

        }
    }
}
