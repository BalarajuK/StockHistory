package com.test;


import com.login.LoginDao;
import com.utils.ObjectContainer;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ename = req.getParameter("name");
        String address = req.getParameter("address");
        String salary = req.getParameter("salary");

        PrintWriter out = resp.getWriter();

        Employee employee = new EmployeeUtils().validateRequest(req);
        if(employee  != null) {
            new EmployeeUtils().updateEmployee(employee, ename, address, salary, new Configuration().configure().buildSessionFactory());
            Cookie ck2=new Cookie("name",employee.getName()+","+employee.getPassword());
            resp.addCookie(ck2);
            new EmployeeUtils().createUpdatePage(employee, resp);

        }

        if(employee ==null){
            //out.print("Please login first");
            req.getRequestDispatcher("login.html").forward(req, resp);
        }

//        if (ename != null && address != null && salary != null) {
//            employee = ObjectContainer.getInstance().getEmployee(ename);
//            new EmployeeUtils().updateEmployee(employee, ename, address, salary, new Configuration().configure().buildSessionFactory());
//
//        } else {
//            employee = new Employee();
//
//        }

        //new EmployeeUtils().createUpdatePage(employee, resp);

    }



    private void searchUpdatePage(Employee employee, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();

        StringBuilder page = new StringBuilder();
        page.append("<html><body>").append("<title>Employee Info</title>").append("<left>")
                .append("<form name=\"Form2\"")
                .append("action=\"http://localhost:8080/test/employee\">")
                .append("<B>Employee Name :</B>").append("<input type=textbox name=\"name\" size=12 value=\"")
                .append(employee.getName()!=null ? employee.getName() :"").append("\">")
                .append("<BR><P><input type=submit value=\"Search\" name=\"Search\\>")
                .append("</form></body> </html>");
        writer.print(page.toString());
    }
}


