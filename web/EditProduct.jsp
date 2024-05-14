<%-- 
    Document   : EditProduct
    Created on : 9 May 2024, 10:53:23 pm
    Author     : User
--%>

<%@page import="da.ProductDA"%>
<%@page import="domain.Product"%>
<%@page import="servlet.EditProduct"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html> 
<head> 
    <title>Update Details</title> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="CSS/All.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/managerCss.css" rel="stylesheet" type="text/css"/>
    <style>
        /* CSS styles for the box */
        .form-box {
            border: 2px solid #ccc;
            padding: 20px;
            margin: 20px auto;
            width: 80%;
            max-width: 600px;
            border-radius: 20px;
        }

        /* CSS styles for the buttons */
        .update-btn,
        .return-btn {
            padding: 10px 40px; /* Adjust the padding as needed */
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            margin-right: 10px;
            display: inline-block;
            transition: background-color 0.3s ease;
        }

        .update-btn:hover,
        .return-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head> 
<body> 
    <%@include file="HeaderNFooter/HeaderAfterLogin_Manager.jsp" %>
    <% Product product = (Product) request.getAttribute("product"); %>
    
    <div class="form-box"> <!-- Start of the box -->
        <h2>Fill in product details</h2> 
        <form action="EditProduct" method="POST" enctype="multipart/form-data"> 
            <table> 
                <tr> 
                    <td>Product ID:</td> 
                    <td><input type="text" name="productID" value="<%=request.getParameter("PID")%>" readonly="readonly"> 
                    </td> 
                </tr> 
                <tr> 
                    <td>Product Name:</td> 
                    <td><input type="text" name="productName" size="50" value="<%=product.getName()%>"/></td> 
                    <td><span name="productName" style="color: red">${errors.name}</span></td>
                </tr> 
                <tr> 
                    <td>Product Description:</td> 
                    <td><input type="text" name="productDesc" size="50" value="<%=product.getDesc()%>"/></td> 
                    <td><span name="productDesc" style="color: red">${errors.desc}</span></td>
                </tr> 
                <tr> 
                    <td>Product Price:</td> 
                    <td><input type="text" name="productPrice" size="10" value="<%=product.getPrice()%>"/></td> 
                    <td><span name="productPrice" style="color: red">${errors.price}</span></td>
                </tr> 
                <tr> 
                    <td>Image:</td> 
                    <td><input type="file" name="productImg" value="<%=product.getImage()%>"/></td> 
                </tr> 
                <tr /> 
            </table> 
            <br> 
            <input type="submit" value="Update Data" class="update-btn" /> 
            <input type="button" value="Return to Product" onclick="window.location.href='ProductStaff.jsp'" class="return-btn" /> 
        </form> 
    </div> <!-- End of the box -->
    
    <div class="footer">
        <%@include file="HeaderNFooter/Footer.jsp" %>
    </div>
</body> 
</html>
