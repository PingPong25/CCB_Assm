/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.sql.*;
import domain.customer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import da.customerDA;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Map<String, String> errors = new HashMap<String, String>();
        HttpSession session = request.getSession(true);
        //compare error
        try {
            String un = request.getParameter("username");
            String password = request.getParameter("password");
            customerDA csDA = new customerDA();
            ResultSet rs = csDA.selectpassword(un, password);
            //request.setAttribute("loginStatus", "login successfull");
            if (rs.next()) {
               
               customer cs2 = new customer(rs.getString("ID"), rs.getString("NAME"), rs.getString("PHONE"), rs.getString("USERNAME"),rs.getString("EMAIL"),rs.getInt("AGE"),rs.getString("ADDRESS"));
               session.setAttribute("customer", cs2);
               RequestDispatcher dispatcher = request.getRequestDispatcher("mainpage_cust.jsp");
               dispatcher.forward(request, response);
                //out.println("welcome");

            } else {
                errors.put("errorlogin", "This account is does not exist or the password is incorrect");
                request.setAttribute("errors", errors); 
                request.getRequestDispatcher("l.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            errors.put("error", "Hit exception :" + ex.getMessage());
        } finally {
            out.close();
        }
    }

}
