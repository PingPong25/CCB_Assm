<%-- 
    Document   : manager_input_id
    Created on : 9 May 2024, 10:15:52 am
    Author     : User
--%>

<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Input Customer ID</title>
     <link href="CSS/All.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/managerCss.css" rel="stylesheet" type="text/css"/>
    <style>
        .containerBox {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin-top: -150px; 
        }

        .box {
            border: 1px solid #ccc;
            padding: 20px;
            width: 400px; 
            background-color: #f9f9f9; 
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); 
            border-radius: 10px; 
        }

        form {
            text-align: center; 
        }

        input#id[type="text"],
        input#sub[type="submit"] {
            padding: 10px;
            margin: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 80%; 
            box-sizing: border-box; 
        }

        input#sub[type="submit"] {
            background-color: #007bff; 
            color: #fff; 
            font-weight: bold; 
            cursor: pointer;
        }

        input#sub[type="submit"]:hover {
            background-color: #0056b3; 
        }

        .footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 20px;
            background-color: #f5f5f5;
            text-align: center;
            line-height: 50px;
        }
        
        input#back[type="button"] {
            padding: 10px;
            margin: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 80%;
            box-sizing: border-box;
            background-color: #007bff;
            color: #fff;
            font-weight: bold;
            cursor: pointer;
        }

        input#back[type="button"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <%@include file="HeaderNFooter/HeaderAfterLogin_Manager.jsp" %>
    <div class="containerBox">
        <div class="box">
            <form action="editCust.jsp" method="post">
                <label for="id">Enter Customer ID:</label><br>
                <input type="text" id="id" name="id" required><br>
                <input type="submit" value="Submit" id="sub">
                <input type="button" value="Back" id="back" onclick="window.location.href='manager_maintain_cust.jsp'">
            </form>
        </div>
    </div>
    <div class="footer">
        <%@include file="HeaderNFooter/Footer.jsp" %>
    </div>
    
</body>

</html>