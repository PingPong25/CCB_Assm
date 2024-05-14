package servlet;

import da.ProductDA;
import domain.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RetrieveProduct", urlPatterns = {"/RetrieveProduct"})
public class RetrieveProduct extends HttpServlet {

    private ProductDA DA = new ProductDA();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printWriter=response.getWriter();    
        //retrieve products from database
        List<Product> productList;
        productList = DA.getAllProducts(); //initialize an empty list in case of an error

        //set the product list
        request.setAttribute("productList", productList);

        //forward the request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditProduct.jsp");
        dispatcher.forward(request, response);
    }
}