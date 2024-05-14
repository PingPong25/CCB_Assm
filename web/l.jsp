<%-- 
    Document   : l
    Created on : May 1, 2024, 6:32:17 PM
    Author     : ROG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="da.customerDA"%>
<%@page import="servlet.login"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
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
          <div class="login-box">
         <h3>Customer Login</h3>
        <form method="post" action="login" >

            <p><label>Username</label>
                <input type="text" name="username" size="40" /></p>

            <p><label>Password</label>
                <input type="password" name="password" size="40" /></p>
            <span name="errorlogin">${errors.errorlogin}</span>
            


            <p><input type="submit" value="Login" />
                </p>
        </form>
            
            <a href ="loginacc.jsp"><button>Register</button></a>
           </div> 
         <div class="footer">
         <%@include file="HeaderNFooter/Footer.jsp" %>
         </div>
    </body>
</html>