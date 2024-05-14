package servlet;

import da.ProductDA;
import domain.Product;
import java.io.ByteArrayOutputStream;
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
import java.util.List;
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

@WebServlet(name = "EditProduct_emp", urlPatterns = {"/EditProduct_emp"})
@MultipartConfig
public class EditProduct_emp extends HttpServlet {

    private double price;
    private final ProductDA DA = new ProductDA();
    private final Product product = new Product();
//    private final String imageDirectory = "C:\\Users\\User\\OneDrive\\Documents\\NetBeansProjects\\Toys\\web\\photo\\";
    
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditProduct_emp.jsp");
        dispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Map<String, String> errors = new HashMap<String, String>();
        Map<String, String> after = new HashMap<String, String>();

        //retrieve form data from the request
        String productID = request.getParameter("productID");
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        String priceStr = request.getParameter("productPrice");
        // Get product image from the form
        Part filePart = request.getPart("productImg");
        InputStream imageInputStream = filePart.getInputStream();

        // Save the image to the directory
//        String fileName = "product_" + productID + ".jpg";
//        String imagePath = imageDirectory + fileName;
        byte[] imageData = readImageBytes(imageInputStream); //declaration for image
        String productType=request.getParameter("productType");
        after.put("name", productName);
        after.put("desc", productDesc);
        after.put("price", String.valueOf(priceStr));
//        after.put("image", imageStr);

        try {
            //check if the product name is valid
            if (productName == null || productName.trim().equals("")) {
                errors.put("name", "Product name cannot be empty.");
            } else {
                ResultSet rs = DA.searchName(productName);
                if (rs.next()) {
                    errors.put("name", "This product name is used. Please change to another product name.");
                }
            }
            
            //check if the product description is valid
            if (productDesc == null || productDesc.trim().equals("")) {
                errors.put("desc", "Please fill in the product description.");
            }
            
            //check if the product price is valid
            //convert price from string to double
            if (priceStr == null || priceStr.trim().equals("")){
                errors.put("price", "Please fill in the price for the product.");
            } else if (priceStr != null && !priceStr.isEmpty()) {
                try {
                    price = Double.parseDouble(priceStr);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            
            //check if the product image is valid
            //decode the Base64 string into a byte array
//            if (imageStr != null && !imageStr.trim().isEmpty()) {
//                // Remove Data URI prefix if present
//                imageStr = imageStr.replaceAll("^data:[^;]*;base64,", "");
//                try {
//                    // Decode the Base64 string into a byte array
//                    byte[] imageData = Base64.getDecoder().decode(imageStr);
//                    // Create a Blob object from the byte array
//                    image = new SerialBlob(imageData);
//                } catch (SQLException | IllegalArgumentException e) {
//                    errors.put("image", "Invalid image data.");
//                }
//            }
//            saveImageToFile(imageInputStream, imagePath);

            //perform adding product using ProductDA class
            if (errors.isEmpty()) {
                Product product = new Product(productID, productName, productDesc, price, imageData);
                DA.updateProduct(product);
                out.println("Prodcut <b>" + productID + "</b> has been updated to database.<br>");
                 request.getRequestDispatcher("emp_maintain_product.jsp").forward(request, response);
            } else {
                request.setAttribute("after", after);
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("EditProduct_emp.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            out.println("<p>" + ex.getMessage() + "</p>");
        }
    }
    
    private byte[] readImageBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[5 * 1024 * 1024]; // Set buffer size as needed
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }
    
    private byte[] getImageBytes(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);
        return Files.readAllBytes(path);
    }

    private void saveImageToFile(InputStream inputStream, String filePath) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[5 * 1024 * 1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    private Product getProduct(String productID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}