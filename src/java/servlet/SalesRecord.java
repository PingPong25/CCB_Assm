package servlet;

import da.SalesDA;
import domain.Sales;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SalesRecord")
public class SalesRecord extends HttpServlet {
    
    int day = 0;
    int month = 0;
    int year = 0;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //retrieve search criteria from request parameters
        try {
            day = Integer.parseInt(request.getParameter("day"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            month = Integer.parseInt(request.getParameter("month"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            year = Integer.parseInt(request.getParameter("year"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        
        SalesDA DA = new SalesDA();
        List<Sales> salesList = null;
        // Check the provided criteria and call the appropriate method in SalesDA
        if (day > 0 && month > 0 && year > 0) {
            // Retrieve daily sales
            salesList = DA.getDailySales(day, month, year);
        } else if (month > 0 && year > 0) {
            // Retrieve monthly sales
            salesList = DA.getMonthlySales(month, year);
        } else if (year > 0) {
            // Retrieve yearly sales
            salesList = DA.getYearlySales(year);
        }
        
        request.setAttribute("salesRecords", salesList);
        request.getRequestDispatcher("Sales.jsp").forward(request, response);
    }
}