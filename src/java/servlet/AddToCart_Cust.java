package servlet;

import domain.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddToCart_Cust", urlPatterns = {"/AddToCart_Cust"})
public class AddToCart_Cust extends HttpServlet {
    
//    // Map to store items in the cart (item ID -> quantity)
//    private Map<String, Integer> cart = new HashMap<>();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html; charset=UTF-8");
        
        try(PrintWriter out = response.getWriter()){
            ArrayList<Cart> cartList = new ArrayList<>();
            
            String PID = request.getParameter("PID");
            Cart cart = new Cart();
            cart.setPID(PID);
            cart.setQuantity(1);
            
            HttpSession session = request.getSession();
            ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
            
            if(cart_list == null){
                cartList.add(cart);
                session.setAttribute("cart-list", cartList);
                response.sendRedirect("Product_cust.jsp");
            }else{
                cartList = cart_list;
                boolean exist = false;
                
                for(Cart c:cart_list){
                    if(c.getPID().equals(PID)){
                        exist = true;
                        response.sendRedirect("Cart_Cust.jsp");
                    }
                }
                
                if(!exist){
                    cartList.add(cart);
                    response.sendRedirect("Product_cust.jsp");
                }
           }
        }
    }
}