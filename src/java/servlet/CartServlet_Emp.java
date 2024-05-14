package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CartServlet_Emp", urlPatterns = {"/CartServlet_Emp"})
public class CartServlet_Emp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printWriter=response.getWriter();

        
        
        HttpSession session = request.getSession();
        Double total = (Double) session.getAttribute("totalAmount");
        session.setAttribute("totalAmount", total);
//        out.println("Total Amount in Servlet: " + total);

//        RequestDispatcher dispatcher = request.getRequestDispatcher("Payment.jsp");
//        dispatcher.forward(request, response);
        response.sendRedirect("Payment_Emp.jsp");
    }
}