package servlet;

import da.customerDA;
import domain.customer;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "updateCust", urlPatterns = {"/updateCust"})
public class updateCust extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve data from the form
        String id = request.getParameter("id");

        // Check if the customer ID exists
        customerDA da = new customerDA();
        if (da.isCustNameExists(id)) {
            // Retrieve other data from the form
            String name = request.getParameter("name");
            String ageStr = request.getParameter("age");
            String address = request.getParameter("address");

            // Validate name, age, and address
            if (name != null && !name.isEmpty() && ageStr != null && !ageStr.isEmpty() && address != null && !address.isEmpty()) {
                try {
                    int age = Integer.parseInt(ageStr);
                    if (age > 0) { 
                        // Age should be a positive integer
                        // Create a customer object with the retrieved data
                        customer cust = new customer();
                        cust.setId(id);
                        cust.setName(name);
                        cust.setAge(age);
                        cust.setAddress(address);

                        // Update the record in the database
                        da.updateCust_manager(cust);

                        // Redirect to the appropriate page after updating
                        response.sendRedirect("manager_maintain_cust.jsp");
                        request.getSession().setAttribute("updateSuccess", true);
                    } else {
                        // Invalid age
                        request.setAttribute("ageError", "Age must be a positive number");
                        System.out.println("Age Error Message: " + request.getAttribute("ageError")); // Debugging
                        request.getRequestDispatcher("editCust.jsp").forward(request, response);
                    }
                } catch (NumberFormatException e) {
                    // Invalid age format
                    request.setAttribute("ageError", "Invalid age format");
                    System.out.println("Age Error Message: " + request.getAttribute("ageError")); // Debugging
                    request.getRequestDispatcher("editCust.jsp").forward(request, response);
                }
            } else {
                // Fields are empty
                request.setAttribute("addressError", "Fields are required");
                request.setAttribute("ageError", "Fields are required");
                request.setAttribute("nameError", "Fields are required");
                System.out.println("Field Error Message: Fields are required");
                request.getRequestDispatcher("editCust.jsp").forward(request, response);
            }
        } else {
            // Customer does not exist, redirect back to input page
            response.sendRedirect("manager_input_id.jsp?error=1");
        }
    }
}
