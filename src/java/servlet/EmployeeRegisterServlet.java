package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;

import da.EmployeeDA;
import domain.Employee;

@WebServlet(name = "EmployeeRegisterServlet", urlPatterns = {"/EmployeeRegisterServlet"})
public class EmployeeRegisterServlet extends HttpServlet {

    private EmployeeDA empDA = new EmployeeDA();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Map<String, String> errors = new HashMap<String, String>();
        Map<String, String> after = new HashMap<String, String>();

        // Retrieve form data from the request
        String empId = request.getParameter("empId");
        String empName = request.getParameter("empName");
        String empPhone = request.getParameter("empPhone");
        String empEmail = request.getParameter("email");
        String userName = request.getParameter("userName");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String job = request.getParameter("job");
        String empPass = request.getParameter("empPass");

        after.put("name", empName);
        after.put("phone", empPhone);
        after.put("email", empEmail);
        after.put("job", job);

        try {
            // Check if the phone number is valid
            if (empPhone == null || empPhone.trim().equals("")) {
                errors.put("phone", "Phone cannot be Empty");
            } else {
                if (!empDA.verifyPhoneNumber(empPhone)) {
                    errors.put("phone", "Invalid phone number. Please enter a valid phone number.");
                } else {
                    ResultSet rs = empDA.searchPhone(empPhone);
                    if (rs.next()) {
                        errors.put("phone", "This phone number is used. please change to another phone number");
                    }
                }
            }

            // Check if the email address is valid
            if (empEmail == null || empEmail.trim().equals("")) {
                errors.put("email", "Email cannot be Empty");
            } else {
                if (!empDA.verifyEmail(empEmail)) {
                    errors.put("email", "Invalid email. Please enter a valid email.");
                } else {
                    ResultSet rs = empDA.searchEmail(empEmail);
                    if (rs.next()) {
                        errors.put("email", "This email is used. please change to another email");
                    }
                }
            }

            // Check if the gender address is valid
            if (gender == null) {
                errors.put("gender", "Please select between Male or Female");
            }

            if (job == null || job.trim().isEmpty()) {
                errors.put("job", "Please enter your position");
            }else{
                if(!empDA.verifyJob(job)){
                    errors.put("job","Please enter a position that is in your database");
                }
            }

            // Check if the name is valid
            String nameErr = empDA.nameValidation(empName);
            if (nameErr.isEmpty()) {

            } else {
                errors.put("name", nameErr);
            }

            // Perform employee registration using EmployeeDA class
            if (errors.isEmpty()) {
                Employee emp = new Employee(empId, empName, empPhone, empEmail, userName, age, gender, job, empPass);
                empDA.registerEmployee(emp);
                request.setAttribute("message", "Employee " + empId + " has been added to database.");
                request.getRequestDispatcher("manager_maintain_emp.jsp").forward(request, response);
            } else {
                request.setAttribute("after", after);
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("Employee_Register.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            out.println("<p>" + ex.getMessage() + "</p>"); // Print stack trace for debugging
        }
    }
}
