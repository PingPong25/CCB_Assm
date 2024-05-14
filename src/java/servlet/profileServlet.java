/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import da.customerDA;
import domain.customer;
import java.sql.*;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ROG
 */
@WebServlet(name = "profileServlet", urlPatterns = {"/profileServlet"})
public class profileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        Map<String, String> error = new HashMap<String, String>();
        Map<String, String> after = new HashMap<String, String>();

        customerDA csDA = new customerDA();
        HttpSession session = request.getSession(true);
        try {
            String id = request.getParameter("id");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String ages = request.getParameter("age");

            after.put("name", name);
            after.put("id", id);
            after.put("username", username);
            after.put("age", ages);
            after.put("phone", phone);
            after.put("email", email);
            after.put("address", address);

            if (!csDA.verifyName(name)) {
                error.put("name", "Name cannot be empty and cannot include number");
            }

            if (address == null || address.trim().equals("")) {
                error.put("address", "Address cannot be empty");
            }
            int age = 0;
            if (ages == null || ages.trim().isEmpty()) {
                error.put("age", "Age cannot be empty");
            } else {
                try {
                    age = Integer.parseInt(ages);
                    if (age < 4 || age > 100) {
                        error.put("age", "Age must be between 4 and 100");
                    }
                } catch (NumberFormatException ex) {
                    error.put("age", "Invalid age. Please input a valid number");
                }
            }

            if (!error.isEmpty()) {
                request.setAttribute("error", error);
                request.setAttribute("after", after);
                RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
                dispatcher.forward(request, response);
                return;
            }

            customer cs = new customer(name, address, age, username);
            csDA.updaterecord(cs);
            customer cs2 = new customer(id, name, phone, username,email,age,address);
            session.setAttribute("customer", cs2);
              

            response.sendRedirect("mainpage_cust.jsp");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("mainPage.jsp");
//            dispatcher.forward(request, response);
            //forwardToPage(request, response, "mainPage.jsp");
            //response.sendRedirect("mainPage.jsp");

        } catch (Exception ex) {
            error.put("error", "Exception occurred: " + ex.getMessage());
        } finally {

        }
    }

    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String mainPagejsp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
