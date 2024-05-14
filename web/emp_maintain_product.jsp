<%-- 
    Document   : emp_maintain_product
    Created on : 10 May 2024, 2:47:15 pm
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>RON's Toys</title>
    <link href="CSS/All.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/managerCss.css" rel="stylesheet" type="text/css"/>
    <style>
        .content {
            margin: 0 auto;
            width: 50%;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 10px;
            
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
        
        .button-add,
        .button-view,
        .button-update,
        .button-delete {
            background-color: green;
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 8px;
        }

        .button-add:hover,
        .button-view:hover,
        .button-update:hover,
        .button-delete:hover {
            background-color: #45a049;
        }
    </style>
</head>

<body>
    <%@include file="HeaderNFooter/HeaderAfterLogin_Emp.jsp" %>

    <div class="container">
        <div class="sidebar-box">
            <div class="sidebar">
                <h2>Welcome</h2>
                <ul>
                    <li>
                        <a href="Emp_Profile.jsp"><b>Profile</b></a>
                    </li>
                    <li class="dropdownBox">
                        <a href=""><b>Manage</b></a>
                        <ul class="dropdownContent">
                            <li><a href="emp_view_cust.jsp">View Customers</a></li>
                            <li><a href="emp_maintain_product.jsp">Product</a></li>
                            <li><a href="AdminReply_emp.jsp">Comment</a></li>
                            <li><a href="AdminViewRating_emp.jsp">Rating</a></li>
                        </ul>
                    </li> 
                    <li>
                        <a href="mainPage.jsp"><b>Logout</b></a>
                    </li>
                </ul>
            </div>
        </div>

        <div class="content"> 
            <h1>Product Maintain</h1>
    
    <form method="POST" action=""> 
        <a href="AddProduct_emp.jsp"><button type="button" class="button-add">Add</button></a> 
        <a href="ViewProduct_emp.jsp"><button type="button" class="button-view">View</button></a> 
    </form>
        </div>

        <div class="sidebar">
            <h1>Hello World!</h1>
        </div>
        
        
    </div>
    
    <div class="footer">
        <%@include file= "HeaderNFooter/Footer.jsp"%>
    </div>
    
</body>
</html>
