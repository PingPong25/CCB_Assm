package servlet;

import da.ProductDA;
import domain.Product;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import java.util.Base64;
import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.sql.rowset.serial.SerialBlob;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "DeleteProduct", urlPatterns = {"/DeleteProduct"})
@MultipartConfig
public class DeleteProduct extends HttpServlet {

    private final ProductDA DA = new ProductDA();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printWriter=response.getWriter();    
        
        String productID = request.getParameter("PID");
        
        // Call a method to retrieve product details based on the product ID
        Product product = null;
        try {
            product = DA.getProduct(productID);
        } catch (SQLException ex) {
            Logger.getLogger(EditProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set the product object as an attribute of the request
        request.setAttribute("product", product);

        //forward the request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("DeleteProduct.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Map<String, String> errors = new HashMap<String, String>();
        Map<String, String> after = new HashMap<String, String>();

        //retrieve form data from the request
        String productID = request.getParameter("productID");

        try { 
            //perform adding product using ProductDA class
            if (errors.isEmpty()) {
                DA.deleteProduct(productID);
                out.println("Prodcut <b>" + productID + "</b> has been deleted.<br>");
                 request.getRequestDispatcher("ProductStaff.jsp").forward(request, response);
            } else {
                request.setAttribute("after", after);
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("DeleteProduct.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            out.println("<p>" + ex.getMessage() + "</p>");
        }
    }
}