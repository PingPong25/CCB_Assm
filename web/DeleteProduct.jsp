<%-- 
    Document   : DeleteProduct
    Created on : 10 May 2024, 2:27:35 am
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Delete Product</title>
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
        .del-btn,
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
        
        <div class="form-box">
        <h2>Delete Product</h2>
        <form action="DeleteProduct" method="POST" enctype="multipart/form-data"> 
        <table> 
            <tr> 
                <td>Product ID:</td> 
                <td><input type="text" name="productID" value="<%=request.getParameter("PID")%>" readonly="readonly"> 
                </td> 
            </tr> 
            <tr /> 
        </table> 
        <br> <input type="submit" value="Delete Data" class="del-btn"/> 
        <br><br> 
        <input type="button" value="Return to Product" onclick="window.location.href='ProductStaff.jsp'" class="return-btn"/> 
        </form> 
                </div>
       
        
         <div class="footer">
        <%@include file="HeaderNFooter/Footer.jsp" %>
        </div>
    </body>
</html>
