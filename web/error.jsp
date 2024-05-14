<%-- 
    Document   : error
    Created on : 8 May 2024, 12:03:00 am
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error</title>
</head>
<body>
    <h1>Error</h1>
    <p>${error}</p>
    <h2>Stack Trace:</h2>
    <ul>
        <% 
            for (StackTraceElement element : (StackTraceElement[]) request.getAttribute("stackTrace")) {
        %>
                <li><%= element.toString() %></li>
        <% 
            } 
        %>
    </ul>
</body>
</html>
