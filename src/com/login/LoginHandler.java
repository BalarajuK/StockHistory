package com.login;


import com.test.Employee;
import com.test.EmployeeUtils;
import org.hibernate.cfg.Configuration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginHandler extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String n = request.getParameter("username");
        String p = request.getParameter("userpass");

        Employee employee = LoginDao.validate(new Configuration().configure().buildSessionFactory(), n, p);
        if (employee != null) {
            request.getRequestDispatcher("link.html").include(request, response);
            Cookie ck = new Cookie("name", employee.getName() + "," + employee.getPassword());
            response.addCookie(ck);
        } else {
            out.print("Sorry username or password error");
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.include(request, response);
        }

        out.close();
    }
}
