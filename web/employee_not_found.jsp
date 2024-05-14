<%-- 
    Document   : employee_not_found
    Created on : 9 May 2024, 5:28:00 pm
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Not Found</title>
    
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5; 
            margin: 0;
            padding: 0;
        }

        .container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 200px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            padding: 20px;
        }

        .error-message {
            color: #e74c3c; 
            font-size: 18px;
            margin-bottom: 20px;
        }

        .back-button {
            background-color: #007bff; 
            color: #fff;
            border: none;
            padding: 12px 24px;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .back-button:hover {
            background-color: #0056b3; 
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
    </style>
</head>
<body>
    <%@include file="HeaderNFooter/HeaderAfterLogin_Manager.jsp" %>
    
    <div class="container">
        <h2>Employee Not Found</h2>
        <p class="error-message">Sorry, the Employee you are looking for does not exist.</p>
        <a href="manager_input_empid.jsp" class="back-button">Go Back</a>
    </div>

    <div class="footer">
        <%@include file="HeaderNFooter/Footer.jsp" %>
    </div>
</body>
</html>
