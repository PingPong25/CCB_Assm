<%-- 
    Document   : DeleteCust
    Created on : 9 May 2024, 2:03:06 pm
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Customers</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="CSS/All.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/managerCss.css" rel="stylesheet" type="text/css"/>
    <style>
        .container2 {
            margin: 20px auto;
            width: 50%;
            border: 1px solid #ccc;
            padding: 30px;
            border-radius: 10px;
            background-color: #f9f9f9;
        }

        .footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 50px;
            background-color: #f5f5f5;
            text-align: center;
            line-height: 50px;
        }
        
        .delete-button {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .delete-button:hover {
            background-color: #c82333; 
        }
        
        .form-group {
            margin-bottom: 20px;
        }

        .delete-button {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .delete-button:hover {
            background-color: #c82333; /* Darker red color on hover */
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
    <%@include file="HeaderNFooter/HeaderAfterLogin_Manager.jsp" %>
    <div class="container2">
    <h1>Delete Customers</h1>
    <form action="deleteCust" method="POST">
        <div class="form-group">
            <label for="cid">Customer ID:</label>
            <input type="text" id="cid" name="cid">
        </div>
        <div class="form-group">
            <input type="submit" value="Delete" class="delete-button">
        </div>
        <div style=" margin-top: 20px;">
                <a href="manager_maintain_cust.jsp" class="back-button">Back</a>
        </div>
    </form>
</div>
    <div class="footer">
        <%@include file= "HeaderNFooter/Footer.jsp"%>
    </div>
</body>
</html>
