package servlet;

import domain.Cart;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RemoveFromCart_Emp", urlPatterns = {"/RemoveFromCart_Emp"})
public class RemoveFromCart_Emp extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String PID = request.getParameter("PID");
            if (PID != null) { 
                ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                if (cartList != null) {
                    // Iterate over the cart list and remove the item with the specified PID
                    for (int i = 0; i < cartList.size(); i++) {
                        if (cartList.get(i).getPID().equals(PID)) {
                            cartList.remove(i);
                            break; // Exit loop after removing the item
                        }
                    }
                }
                response.sendRedirect("Cart_Emp.jsp");
            } else {
                response.sendRedirect("Cart_Emp.jsp");
            }
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            response.sendRedirect("Cart_Emp.jsp"); // Redirect to cart page in case of error
        }
    }
}
