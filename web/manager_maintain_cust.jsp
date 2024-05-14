<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
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
    <%@include file="HeaderNFooter/HeaderAfterLogin_Manager.jsp" %>

        
    <div class="container">
        <div class="sidebar-box">
            <div class="sidebar">
                <h2>Welcome Boss</h2>
                <ul>
                    <li>
                        <a href="manager_Profile.jsp"><b>Profile</b></a>
                    </li>
                    <li class="dropdownBox">
                        <a href=""><b>Manage User</b></a>
                        <ul class="dropdownContent">
                            <li><a href="manager_maintain_cust.jsp">Customer</a></li>
                            <li><a href="manager_maintain_emp.jsp">Staff</a></li>
                            <li><a href="manager_maintain_product.jsp">Product</a></li>
                            <li><a href="AdminReply.jsp">Comment</a></li>
                            <li><a href="AdminReply.jsp">Comment</a></li>
                            <li><a href="AdminViewRating.jsp">Rating</a></li>
                        </ul>
                    </li> 
                    <li>
                        <a href="mainPage.jsp"><b>Logout</b></a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="content">   
    <h1>Customer Maintain</h1>
    
    <form method="POST" action="">
        <a href="addcust.jsp"><button type="button" class="button-add">Add</button></a>
        <a href="ViewCust.jsp"><button type="button" class="button-view">View</button></a>
        <a href="manager_input_id.jsp"><button type="button" class="button-update">Update</button></a>
        <a href="DeleteCust.jsp"><button type="button" class="button-delete">Delete</button></a>  
    </form>
    
    <% 
    // Check if the add was successful
    Boolean addSuccess = (Boolean) request.getSession().getAttribute("addSuccess");
    if (addSuccess != null) {
        if (addSuccess) {
    %>
            <p>Customer added successfully!</p>
    <%
        } else {
    %>
            <p>Error: Failed to add customer.</p>
    <%
        }
        // Clear the session attribute
        request.getSession().removeAttribute("addSuccess");
    }
    %>
    
        <%
    // Check if the update was successful
    Boolean updateSuccess = (Boolean) request.getSession().getAttribute("updateSuccess");
    if (updateSuccess != null) {
        if (updateSuccess) {
    %>
            <p>Customer updated successfully!</p>
    <%
        } else {
    %>
            <p>Error: Failed to update customer.</p>
    <%
        }
        // Clear the session attribute
        request.getSession().removeAttribute("updateSuccess");
    }
    %>

    <%
    // Check if the delete was successful
    Boolean deleteSuccess = (Boolean) request.getSession().getAttribute("deleteSuccess");
    if (deleteSuccess != null) {
        if (deleteSuccess) {
    %>
            <p>Customer deleted successfully!</p>
    <%
        } else {
    %>
            <p>Error: Failed to delete customer.</p>
    <%
        }
        // Clear the session attribute
        request.getSession().removeAttribute("deleteSuccess");
    }
    %>
    </div>
    
    
    <div class="sidebar">
            <h1></h1>
    </div>
 
    </div>
    
    <div class="footer">
        <%@include file= "HeaderNFooter/Footer.jsp"%>
    </div>
</body>
</html>
