package servlet;

import domain.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductQuantity_Emp", urlPatterns = {"/ProductQuantity_Emp"})
public class ProductQuantity_Emp extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            String PID = request.getParameter("PID");
            ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

            if (action != null && PID != null) {
                if (action.equals("inc")) {
                    for (Cart c:cartList) {
                        if (c.getPID().equals(PID)) {
                            int quantity = c.getQuantity();
                            quantity++;
                            c.setQuantity(quantity);
                            response.sendRedirect("Cart_Emp.jsp");
                        }
                    }
                }

                if (action.equals("dec")) {
                    for (Cart c:cartList) {
                        if (c.getPID().equals(PID) && c.getQuantity() > 1) {
                            int quantity = c.getQuantity();
                            quantity--;
                            c.setQuantity(quantity);
                            break;
                        }
                    }
                        response.sendRedirect("Cart_Emp.jsp");
                }
            } else {
                response.sendRedirect("Cart_Emp.jsp");
            }
        }
    }
}