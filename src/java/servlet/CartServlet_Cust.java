package servlet;

import da.SalesDA;
import domain.Sales;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/CartServlet_Cust"})
public class CartServlet_Cust extends HttpServlet {

    private final SalesDA DA = new SalesDA();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printWriter=response.getWriter();

        HttpSession session = request.getSession();
        String SID = null;
        try {
            SID = DA.generateNewSalesID();
        } catch (SQLException ex) {
            Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String PID = (String) session.getAttribute("PID");
        Integer quantity = (Integer) session.getAttribute("quantity");
        Double revenue = (Double) session.getAttribute("subtotal");
        int day = DA.getDay(new Date());
        int month = DA.getMonth(new Date());
        int years = DA.getYear(new Date());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Double total = (Double) session.getAttribute("totalAmount");
        
        Sales sales = new Sales(SID, PID, quantity, revenue, day, month, years, timestamp);
        try {
            DA.addSales(sales);
        } catch (SQLException ex) {
            Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        session.setAttribute("totalAmount", total);

        response.sendRedirect("Payment_Cust.jsp");
    }
}