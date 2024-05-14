<%-- 
    Document   : NewPassword
    Created on : May 3, 2024, 8:39:21 PM
    Author     : Adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="da.EmployeeDA"%>
<%@page import="servlet.NewPasswordServlet"%>

<!-- Display login status if available -->
<% 
    String userName = (String) request.getAttribute("username");
    String loginStatus = (String) request.getAttribute("loginStatus");
    String errorMessage = (String) request.getAttribute("errorMessage");

    // Retrieve the values of newPass and cfmPass if available
    String newPassValue = (String) request.getAttribute("newPass");
    String cfmPassValue = (String) request.getAttribute("cfmPass");
%>

<!DOCTYPE html> 
<html>
<head>
    <title>New Password</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="CSS/All.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/managerCss.css" rel="stylesheet" type="text/css"/>
    <style>
        .footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 50px; /* Adjust height of the footer as needed */
            background-color: #f5f5f5; /* Example background color */
            text-align: center;
            line-height: 50px; /* Vertical centering of text */
        }
    </style>
</head>
<body>
    <%@include file="HeaderNFooter/Hearder.jsp" %>
    <h3 style="text-align: center">New Password</h3>
 
    <% if (loginStatus != null) { %>
        <p style="text-align: center"><%= loginStatus %></p>
    <% } else if (errorMessage != null) { %>
    <p style="color: red"><%= errorMessage %></p>
    <% } %>

    <div style="text-align: center;">
    <form action="NewPasswordServlet" method="POST">
        <% if(errorMessage != null){%>
        <div style="color: red">
            <% }else{ %>
         <div>
            <% } %>
            <p>Password must have at least one digit</p>
            <p>Password must have at least one character</p>
            <p>Password must have at least 7 characters</p>
        </div>
        <p><label>New Password</label>
            <input type="password" name="newPass" size="10" /></p>
        
        <p><label>Confirmation Password</label>
            <input type="password" name="cfmPass" size="10" /></p>
        <input type="hidden" name="userName" value="<%= userName%>"/>
        <p>
            <input type="submit" value="Set Password" />
        </p>
    </form>
</div>
        <div class="footer">
         <%@include file="HeaderNFooter/Footer.jsp" %>
         </div>
</body>
</html>
