/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import domain.customer;
import da.customerDA;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "mainPageServlet", urlPatterns = {"/mainPageServlet"})
public class mainPageServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("hello world");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        PrintWriter out = response.getWriter();
        try {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cid = request.getParameter("id");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String age = request.getParameter("age");

            
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("id", cid);
            request.setAttribute("name", name);
            request.setAttribute("phone", phone);
            request.setAttribute("email", email);
            request.setAttribute("address", address);
            request.setAttribute("age", age);
            RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
            dispatcher.forward(request, response);
            response.sendRedirect("profile.jsp");

            //request.getRequestDispatcher("profile.jsp");
            //request.(request, response);
        } catch (Exception ex) {
//            request.setAttribute("error", "Hit exception :" + ex.getMessage());
//            RequestDispatcher dispatcher;
//
//            dispatcher = request.getRequestDispatcher("mainPage.jsp");
//
        }
    }

}
