<%-- 
    Document   : mainLogin
    Created on : 9 May 2024, 9:36:41 pm
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main Login</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        text-align: center;
        background-color: lightcyan;
    }

    h1 {
        margin-bottom: 20px;
    }

    button {
        padding: 10px 20px;
        font-size: 16px;
        border: none;
        border-radius: 5px;
        background-color: #4CAF50;
        color: white;
        cursor: pointer;
        margin: 10px;
    }

    button:hover {
        background-color: #45a049;
    }

    form {
        display: inline-block;
    }
    
        .login-box {
        border: 1px solid #ccc;
        padding: 30px;
        margin: 50px auto 0;
        width: 300px;
        border-radius: 10px;
        background-color: #f9f9f9;
        margin-top:20%;
    }
    </style>
</head>
<body>
    <div class="login-box">
        <h1>Main Login</h1>
        <form method="post" action="l.jsp">
            <button type="submit">Customer Login</button>
        </form>
        <form method="post" action="Employee_Login.jsp">
            <button type="submit">Employee Login</button>
        </form>
    </div>
</body>
</html>
