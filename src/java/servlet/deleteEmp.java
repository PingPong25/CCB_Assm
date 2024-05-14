/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import da.EmployeeDA;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "deleteEmp", urlPatterns = {"/deleteEmp"})
public class deleteEmp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        try {
            EmployeeDA daEmp = new EmployeeDA();
            boolean isEmpRecordDeleted = daEmp.deleteRecord(id);

            if (isEmpRecordDeleted) {
                // Record deleted successfully
                request.setAttribute("message", "Record deleted successfully");
            } else {
                // Record does not exist
                request.setAttribute("message", "Record with ID " + id + " does not exist");
            }

            // Redirect back to the manager_maintain_cust.jsp
            request.getRequestDispatcher("manager_maintain_emp.jsp").forward(request, response);
            request.getSession().setAttribute("deleteSuccess", true);
        } catch (Exception ex) {
            // Handle any exceptions
            request.setAttribute("error", "An error occurred: " + ex.getMessage());
            request.setAttribute("stackTrace", ex.getStackTrace());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}