package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import da.EmployeeDA;
import java.sql.ResultSet;

@WebServlet(urlPatterns = {"/EmployeeLoginServlet"})
public class EmployeeLoginServlet extends HttpServlet {

    private EmployeeDA loginDA = new EmployeeDA();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data from the request
        String userName = request.getParameter("userName");
        String password = request.getParameter("passWord");

        Map<String, String> errors = new HashMap<String, String>();

        try {

            if (userName == null || userName.trim().equals("")) {
                errors.put("error", "UserName or Password is incorrect");
            }

            if (password == null || password.trim().equals("")) {
                errors.put("error", "UserName or Password is incorrect");
            }

            // Check if the username is special case "M0001" with password "ABC123"
            if (userName.equals("M0001") && password.equals("ABC123")) {
                // Redirect to another page for special case "M0001"
                request.setAttribute("ManagerID", "M0001");
                response.sendRedirect("manager_Profile.jsp");
                return; // Exit the method
            }

            // Perform employee login using EmployeeDA class
            ResultSet rs = loginDA.searchPassword(userName);

            // Check if the username exists in the database
            if (rs.next()) {
                String actualPassword = rs.getString("EMPLOYEE_PASSWORD");
                // Check if the entered password matches the stored password
                if (actualPassword.equals(password)) {
                    if (password.equals("ABC123")) {
                        // Login successful, forward to change password page
                        request.setAttribute("loginStatus", "Employee " + userName + ". Please Change Your Password to Something You can Remember.");
                        request.setAttribute("username", userName);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("NewPassword.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("Emp_Profile.jsp");
                        dispatcher.forward(request, response);
                    }
                } else {
                    errors.put("error", "UserName or Password is incorrect");
                    request.setAttribute("errors", errors);
                    request.getRequestDispatcher("Employee_Login.jsp").forward(request, response);
                }
            } else if (!rs.next()) { 
                    errors.put("error", "UserName or Password is incorrect");
                    request.setAttribute("errors", errors);
                    request.getRequestDispatcher("Employee_Login.jsp").forward(request, response);
                }

        } catch (SQLException ex) {
            // Handle database connection errors
            ex.printStackTrace();
            // Forward to an error page
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

// Method to forward to the login page with appropriate message
    private void forwardToLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Employee_Login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "EmployeeLoginServlet";
    }
}