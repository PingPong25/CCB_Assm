<%-- 
    Document   : Employee_Login
    Created on : Apr 30, 2024, 10:40:27 PM
    Author     : Adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="da.EmployeeDA"%>
<%@page import="servlet.EmployeeLoginServlet"%>

<% 
    String loginStatus = (String) request.getAttribute("loginStatus");
    %>
    
<!DOCTYPE html> 
<html>
    <head>
        <title>Employee Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/All.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/managerCss.css" rel="stylesheet" type="text/css"/>
    <style>
         .login-box {
            border: 1px solid #ccc;
            padding: 30px;
            margin: 50px auto 0; 
            width: 300px;
            border-radius: 10px; 
            background-color: #f9f9f9;
            
        .footer {
            position: absolute;
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
        <%@include file="HeaderNFooter/Hearder.jsp" %>
       <div class="login-box"> <!-- Box for the form -->
        <h3>Employee Login</h3>
        <% if (loginStatus != null) { %>
            <p><%= loginStatus %></p>
        <% } %>
        <form action="EmployeeLoginServlet" method="POST">
            <p><label>UserName</label>
                <input type="text" name="userName" size="10"/></p>

            <p><label>Password</label>
                <input type="password" name="passWord" size="10" /></p>
            <span name="employeeName" style="color: red">${errors.error}</span>
            <p>
                <input type="submit" value="Login" />
            </p>
        </form>
    </div>
         <div class="footer">
         <%@include file="HeaderNFooter/Footer.jsp" %>
         </div>
    </body>
</html>
