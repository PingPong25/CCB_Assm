<%-- 
    Document   : AddProduct_emp
    Created on : 9 May 2024, 8:00:17 pm
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="da.ProductDA"%>
<%@page import="servlet.AddProduct"%>

<!DOCTYPE html> 
<html>
    <head>
        <title>Add New Product</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/All.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/managerCss.css" rel="stylesheet" type="text/css"/>
        <style>
            .container {
                margin: 20px auto;
                width: 50%;
                border: 1px solid #ccc;
                padding: 30px;
                border-radius: 10px;
                background-color: #f9f9f9;
            }

            .footer {
            position: fixed;
            bottom: 0;
            width: 100%;
            height: 50px; 
            background-color: #f5f5f5; 
            text-align: center;
            line-height: 50px;
        }
        
        .blue-button {
            background-color: #007bff; /* Blue color */
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
        }

        .blue-button:hover {
            background-color: #0056b3; /* Darker blue color on hover */
        }
        
        a.back-button {
            display: inline-block;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background-color: #007bff;
            color: #fff; 
            text-decoration: none; 
            cursor: pointer;
        }

        a.back-button:hover {
            background-color: #0056b3; 
        }
        </style>
    </head>
    <body>
        <%@include file="HeaderNFooter/HeaderAfterLogin_Emp.jsp" %>
        <h3 style="text-align:center;">New Product Details</h3> 
        <div class="container">
            


            <%
                //generate new product ID and store it in a variable
                ProductDA DA = new ProductDA();
                String productId = DA.generateNewProductID();
            %>

            <form action="AddProduct_emp" method="POST" enctype="multipart/form-data">
                <p><label>Product ID</label>
                    <input type="text" name="productID" size="10" value="<%= productId%>" readonly/></p>
                <p><label>Product Name</label>
                    <input type="text" name="productName" size="50" value="${after.name}"/></p>
                <span name="productName" style="color: red">${errors.name}</span>
                <p><label>Product Description</label>
                    <input type="text" name="productDesc" size="50" value="${after.desc}"/></p> 
                <span name="productDesc" style="color: red">${errors.desc}</span>
                <p><label>Product Price</label>
                    <input type="text" name="productPrice" size="10" value="${after.price}"/></p>
                <span name="productPrice" style="color: red">${errors.price}</span>
                <p><label>Image</label>
                    <input type="file" name="productImg" value="${after.image}"/></p>
                <span name="productImg" style="color: red">${errors.image}</span>

                <input type="submit" value="Submit" class="blue-button"/>
                <input type="reset" value="Reset" class="blue-button"/>
            <div style=" margin-top: 20px;">
                <a href="emp_maintain_product.jsp" class="back-button">Back</a>
            </div>
            </form>
        </div>
        <div class="footer">
            <%@include file="HeaderNFooter/Footer.jsp" %>
        </div>
    </body>
</html>
