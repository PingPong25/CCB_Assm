package servlet;

import da.PaymentDA;
import da.ProductDA;
import domain.Payment;
import domain.Product;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(urlPatterns = {"/PaymentServlet_Cust"})
public class PaymentServlet_Cust extends HttpServlet {
    
    private final PaymentDA DA = new PaymentDA();
    private int cvv;
    private double totalAmount;

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//        PrintWriter printWriter=response.getWriter();
//        
//        double total = Double.parseDouble(request.getParameter("total"));
//        
//        request.setAttribute("total", total);
//        
//        RequestDispatcher dispatcher = request.getRequestDispatcher("Payment.jsp");
//        dispatcher.forward(request, response);
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        

        Map<String, String> errors = new HashMap<String, String>();
        Map<String, String> after = new HashMap<String, String>();
        

        //retrieve form data from the request
        String payID = request.getParameter("payID");
        String Cid = request.getParameter("Cid");
        String CName = request.getParameter("CName");
        String address = request.getParameter("address");
        String method = request.getParameter("method");
        String nameOnCard = request.getParameter("nameOnCard");
        String cardNumber = request.getParameter("cardNumber");
        String expiryDate = request.getParameter("expiryDate");
        String cvvStr = request.getParameter("cvv");
        String totalAmountStr = request.getParameter("totalAmount");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        

        after.put("Cid", Cid);
        after.put("name", CName);
        after.put("address", address);
        after.put("method", method);
        after.put("nameOnCard", nameOnCard);
        after.put("cardNumber", cardNumber);
        after.put("expiryDate", expiryDate);
        after.put("cvv", cvvStr);
        after.put("totalAmount", totalAmountStr);

        try { 
            //check if the id is valid
            if (Cid == null || Cid.trim().equals("")) {
                errors.put("Cid", "Your ID cannot be empty.");
            }
            
            //check if the name is valid
            if (CName == null || CName.trim().equals("")) {
                errors.put("name", "Your name cannot be empty.");
            }
            
            //check if the address is valid
            if (address == null || address.trim().equals("")) {
                errors.put("address", "Please fill in the shipping address.");
            }
            
            //check if the payment method is valid
            if (method == null || method.trim().equals("")){
                errors.put("method", "Please select a payment method.");
            }
            
           // If payment method is not "cash", validate credit card information
if (!method.equalsIgnoreCase("cash")) {
    // Check if the name on card is valid
    if (nameOnCard == null || nameOnCard.trim().equals("")){
        errors.put("nameOnCard", "Please fill in the name on the card.");
    }

    // Check if the card number is valid
    if (cardNumber == null || cardNumber.trim().equals("")){
        errors.put("cardNumber", "Please fill in the card number.");
    }

    // Check if the expiry date is valid
    if (expiryDate == null || expiryDate.trim().equals("")){
        errors.put("expiryDate", "Please select an expiry date for your card.");
    }

    // Check if the CVV is valid
    if (cvvStr == null || cvvStr.trim().equals("")) {
        errors.put("cvv", "Please fill in the CVV.");
    } else {
        try {
            cvv = Integer.parseInt(cvvStr);
            if (cvv < 100 || cvv > 999) {
                errors.put("cvv", "CVV must be a 3-digit number.");
            }
        } catch (NumberFormatException e) {
            errors.put("cvv", "CVV must be a numeric value.");
        }
    }
}
            
            //check if the total amount is valid
            //convert total amount from string to double
            if (totalAmountStr != null && !totalAmountStr.isEmpty()) {
                try {
                    totalAmount = Double.parseDouble(totalAmountStr);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            //perform adding product using PaymentDA class
            if (errors.isEmpty()) {
            Payment payment = new Payment(payID, method, nameOnCard, cardNumber, expiryDate, cvv, totalAmount, CName, address, Cid, timestamp);                
            DA.addPayment(payment);
                session.removeAttribute("cart-list");
                response.sendRedirect("Rating_Cust.jsp");
                
            } else {
                request.setAttribute("after", after);
                request.setAttribute("errors", errors);
                request.setAttribute("Cid", Cid);
                request.setAttribute("name", CName);
                request.setAttribute("address", address);
                request.setAttribute("totalAmount", totalAmountStr);
                request.getRequestDispatcher("Payment_Cust.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            out.println("<p>" + ex.getMessage() + "</p>");
        }
    }
}

