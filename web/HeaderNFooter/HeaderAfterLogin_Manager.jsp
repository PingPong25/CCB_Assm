<%-- 
    Document   : HeaderAfterLogin
    Created on : 9 May 2024, 3:10:10 pm
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <title>Header</title>
    <head>
    <style>
        header {
            background-color: lightcyan;
            color: black;
            padding: 10px;
            text-align: center;
        }
    </style>
    </head>
<body>
    <header> 
    <div>
        <a href="mainPage_Manager.jsp">
                    <img src="photo/logo.png" alt="LOGO" width="150" height="150" 
            style="margin-right: 100%; margin-bottom: -120px; mix-blend-mode: darken;">
        </a>
                <h1>TARUMT Univerisity</h1>
    </div>
        
        
        
        <br>
        
        <nav class="navbox">
            <ul style="background-color: lightskyblue; border-radius: 10px;">
            <li><a href="mainPage_Manager.jsp">🏠 Home</a></li>
            <li>
                <a>Products</a>
                <ul>
                    <li><a href="Product.jsp">All Products</a></li>

                </ul> 
            </li>
            <li><a href="Cart.jsp">🛒 Cart</a></li>
            <li><a href="Sales.jsp">🛒 Sale Report</a></li>
            <li><a style="font-size: 20px; text-align: center">🧑🏻‍💼 </a>   
                <ul>
                    <li><a href ="mainPage.jsp">Logout</a></li>
                    <li><a href ="manager_Profile.jsp">View Profile</a></li>
                </ul>
            </li> 
            </ul>        
        </nav>
        
    </header>
</body>
</html>