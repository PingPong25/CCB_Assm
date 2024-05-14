/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import da.customerDA;
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

@WebServlet(name = "deleteCust", urlPatterns = {"/deleteCust"})
public class deleteCust extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");

        try {
             customerDA cust = new customerDA();
            boolean isRecordDeleted = cust.deleteRecord(cid);

            if (isRecordDeleted) {
                // Record deleted successfully
                request.setAttribute("message", "Record deleted successfully");
            } else {
                // Record does not exist
                request.setAttribute("message", "Record with ID " + cid + " does not exist");
            }

            // Redirect back to the manager_maintain_cust.jsp
            request.getRequestDispatcher("manager_maintain_cust.jsp").forward(request, response);
            request.getSession().setAttribute("deleteSuccess", true);
        } catch (Exception ex) {
            // Handle any exceptions
            request.setAttribute("error", "An error occurred: " + ex.getMessage());
            request.setAttribute("stackTrace", ex.getStackTrace());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}