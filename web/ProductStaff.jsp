<%-- 
    Document   : ProductStaff
    Created on : 10 May 2024, 3:35:00 pm
    Author     : User
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.util.List"%>
<%@page import="da.ProductDA"%>
<%@page import="domain.Product"%>
<%
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String message = ""; // Declare and initialize the message variable

    try {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CCB", "nbuser", "nbuser");
        stmt = conn.createStatement();
        ProductDA da = new ProductDA();
        rs = stmt.executeQuery("SELECT * FROM PRODUCT");

        List<Product> productList = da.getAllProducts();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cart</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/All.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/managerCss.css" rel="stylesheet" type="text/css"/>
        <style>
            table {
                margin: 0 auto;
            }


            table {
                border-collapse: collapse;
                width: 80%;
            }

            th, td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #f2f2f2;
            }

            tr:hover {
                background-color: #f5f5f5;
            }


            .back-btn {
                margin-top: 20px; /* Adjust the margin from the top */
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                text-decoration: none;
                margin-left: 75%;
                margin-right: 0;
                display: inline-block; /* Make the button a block element */
            }

            .back-btn:hover {
                background-color: #0056b3;
            }
        </style>    
    </head>
    <body>
        <%@include file="HeaderNFooter/HeaderAfterLogin_Manager.jsp" %>
        <h1 style="text-align: center;">Product Details</h1>

        <table>
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Product Description</th>
                    <th>Product Price</th>
                    <th>Product Image</th>
                    <th>Edit Product</th>
                    <th>Delete Product</th>
                </tr>
            </thead>

            <tbody>
                <%
                     while (rs.next()) {

                    String productId = rs.getString(1);
                    String productName = rs.getString(2);
                    String productDescription = rs.getString(3);
                    double productPrice = rs.getDouble(4);

                    // Display the image
                    String base64Image = "";
                    Blob imageBlob = rs.getBlob(5);
                    if (imageBlob != null) {
                        byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());
                        base64Image = java.util.Base64.getEncoder().encodeToString(imageData);
                    }
                %>
                <tr>
                    <td><%=productId%></td>
                    <td><%=productName%></td>
                    <td><%=productDescription%></td>
                    <td><%=productPrice%></td>
                    <td><img src="data:image/jpeg;base64, <%= base64Image%>" alt="Product Image" width="100px"></td>

                    <td><a href="EditProduct?PID=<%= productId%>">Edit</a></td>
                    <td><a href="DeleteProduct?PID=<%= productId%>">Delete</a></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>  

        <a href="manager_maintain_product.jsp" class="back-btn">Back</a>

        <div class="footer">
            <%@include file="HeaderNFooter/Footer.jsp" %>
        </div>
<%
    }
    catch (SQLException e) {
        e.printStackTrace();
    }

    finally {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
%>
 
</body>
</html>
