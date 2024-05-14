/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import domain.customer;
import da.customerDA;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "register", urlPatterns = {"/register"})
public class register extends HttpServlet {


    customerDA csDA = new customerDA();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Map<String, String> errors = new HashMap<String, String>();
        Map<String, String> after = new HashMap<String, String>();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));
        String address = request.getParameter("address");
        customer cust = new customer(id, name, phone, username, password, email, age, address);
        cust.setId(id);
        cust.setName(name);
        cust.setPhone(phone);
        cust.setUsername(username);
        cust.setPassword(password);
        cust.setEmail(email);
        cust.setAge(age);
        cust.setAddress(address);
        

        try {
            String nameErr = csDA.nameValidation(name);
            if (nameErr.isEmpty()) {

            } else {
                errors.put("name", nameErr);
            }

            if (phone == null || phone.trim().equals("")) {
                errors.put("phone", "Phone cannot be empty");
            } else {
                if (!csDA.verifyPhoneNumber(phone)) {
                    errors.put("phone", "Invalid phone number. Please enter a valid phone number.");
                } else {
                    ResultSet rs = csDA.selectPhone(phone);
                    if (rs.next()) {
                        errors.put("phone", "This phone number is used. please change the other phone number");
                    }
                }
            }

            if (username == null || username.trim().equals("")) {
                errors.put("username", "username cannot be empty");
            } else {
                ResultSet rs = csDA.selectRecord(username);
                if (rs.next()) {
                    errors.put("username", "The username is used. please change the other username");
                }
            }

//            String pswErr = csDA.passwordValidation(password);
//            if (pswErr.isEmpty()) {
//                if (comfirmPassword.equals(password)) {
//
//                } else {
//                    errors.put("password", "comfirm password must be same with password");
//                }
//            } else {
//                errors.put("password", pswErr);
//            }

            if (email == null || email.trim().equals("")) {
                errors.put("email", "Email cannot be empty");
            } else {
                if (!csDA.verifyEmail(email)) {
                    errors.put("email", "Invalid email. Please enter a valid email.");
                } else {
                    ResultSet rs = csDA.selectEmail(email);
                    if (rs.next()) {
                        errors.put("email", "The email is used. please change the other email");
                    }
                }
            }
            if (address == null || address.trim().equals("")) {
                errors.put("address", "Address cannot be empty");
            }
            if (errors.isEmpty()) {
                customer cs = new customer(id, name, phone, username, password, email, age, address);
                csDA.addRecord(cs);
                out.println("Student <b>" + id + "</b>Has been added to database.<br>");
                request.getRequestDispatcher("l.jsp").forward(request, response);
            } else {
                request.setAttribute("after", after);
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("loginacc.jsp").forward(request, response);
            }
            
            } catch (Exception ex) {
                // Handle other exceptions
                errors.put("error", "Exception occurred: " + ex.getMessage());
                ex.printStackTrace(); // Print stack trace for debugging
            } finally {
                out.close();
            }
        
    }
}
    




