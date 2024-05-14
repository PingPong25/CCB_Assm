package servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import da.EmployeeDA;
import domain.Employee;
import java.sql.ResultSet;

@WebServlet(name = "NewPasswordServlet", urlPatterns = {"/NewPasswordServlet"})
public class NewPasswordServlet extends HttpServlet {

    private EmployeeDA loginDA = new EmployeeDA();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        // Retrieve form data from the request
        String newPass = request.getParameter("newPass");
        String cfmPass = request.getParameter("cfmPass");
        String userName = request.getParameter("userName"); // assuming you have a username field in your form

        try {

            //Validate password
            boolean passwordValid = loginDA.isPasswordValid(newPass);
            if (!passwordValid) {
                request.setAttribute("errorMessage", "Invalid password format");
                request.setAttribute("username", userName);
                forwardToPage(request, response, "/NewPassword.jsp");
            } else {
                if (!cfmPass.equals(newPass)) {
                    request.setAttribute("errorMessage", "Your Passwords do not match");
                    request.setAttribute("username", userName);
                    forwardToPage(request, response, "/NewPassword.jsp");
                } else {
                    // Passwords match and are valid
                    Employee emp = new Employee(cfmPass, userName);
                    ResultSet rs = loginDA.searchEmployee(userName); // Get Employee object by username
                    if (rs.next()) {
                        emp.setEmpPass(cfmPass); // Update the password
                        loginDA.updatePassword(emp); // Call the updatePassword method with the Employee object
                        forwardToPage(request, response, "/Employee_Login.jsp");
                    }
                }
            }

        } catch (SQLException ex) {
            // Handle exceptions appropriately
            request.setAttribute("errorMessage", "An error occurred: " + ex.getMessage());
            forwardToPage(request, response, "/NewPassword.jsp");
        }

        // Update password associated with the username
    }

    // Helper method to forward the request to a JSP page
    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
