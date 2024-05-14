/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import da.EmployeeDA;
import domain.Employee;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "updateEmp", urlPatterns = {"/updateEmp"})
public class updateEmp extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve data from the form
        String id = request.getParameter("id");

        // Check if the Employee ID exists
        EmployeeDA daEmp = new EmployeeDA();
        if (daEmp.isEmpNameExists(id)) {
            // Customer exists, proceed with updating
            // Retrieve other data from the form
            String name = request.getParameter("name");
            String age = request.getParameter("age");
            String job = request.getParameter("job");

            // Create a Employee object with the retrieved data
            if (name != null && !name.isEmpty() && age != null && !age.isEmpty() && job != null && !job.isEmpty()){
                try{
            int ages = Integer.parseInt(age);
            if(ages >0){
            
            Employee Emp = new Employee();
            Emp.setEmpId(id);
            Emp.setEmpName(name);
            Emp.setEmpAge(ages);
            Emp.setEmpJob(job);
            

            // Update the record in the database
            daEmp.updateEmp_manager(Emp);

            // Redirect to the appropriate page after updating
            response.sendRedirect("manager_maintain_emp.jsp");
            request.getSession().setAttribute("updateSuccess", true);
            } else {
                        // Invalid age
                        request.setAttribute("ageError", "Age must be a positive number");
                        System.out.println("Age Error Message: " + request.getAttribute("ageError")); // Debugging
                        request.getRequestDispatcher("editEmp.jsp").forward(request, response);
                    }
                } catch (NumberFormatException e) {
                    // Invalid age format
                    request.setAttribute("ageError", "Invalid age format");
                    System.out.println("Age Error Message: " + request.getAttribute("ageError")); // Debugging
                    request.getRequestDispatcher("editEmp.jsp").forward(request, response);
                }
            } else {
                // Fields are empty
                request.setAttribute("jobError", "Fields are required");
                request.setAttribute("ageError", "Fields are required");
                request.setAttribute("nameError", "Fields are required");
                System.out.println("Field Error Message: Fields are required");
                request.getRequestDispatcher("editEmp.jsp").forward(request, response);
            }
        } else {
            // Customer does not exist, redirect back to input page
            response.sendRedirect("manager_input_empid.jsp?error=1");
        }
    }
}
