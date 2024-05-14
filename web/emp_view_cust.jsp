<%-- 
    Document   : emp_view_cust
    Created on : 10 May 2024, 1:06:44 am
    Author     : User
--%>

<%@page import="java.sql.ResultSet"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="da.customerDA"%>
<%@page import="domain.customer"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Customer Details</title>
    <link href="CSS/All.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/managerCss.css" rel="stylesheet" type="text/css"/>
    <style>
        table {
            margin: 0 auto;
        }
        
        
        table {
            border-collapse: collapse;
            width: 80%;
        }
        
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        
        th {
            background-color: #f2f2f2;
        }
        
        tr:hover {
            background-color: #f5f5f5;
        }
        
        
        .back-btn {
            margin-top: 20px; /* Adjust the margin from the top */
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            margin-left: 75%;
            margin-right: 0;
            display: inline-block; /* Make the button a block element */
        }
        
        .back-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <%@include file="HeaderNFooter/HeaderAfterLogin_Emp.jsp" %>
    <h1 style="text-align: center;">Customer Details</h1>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Username</th>
                <th>Age</th>
                <th>Address</th>
            </tr>
        </thead>
        <tbody>
            <% 
            try {
                // Create an instance of daCust_manager to access its methods
                customerDA cust = new customerDA();
                // Retrieve all customer records
                ResultSet rs = cust.getAllCustomerRecords();
                // Loop through the ResultSet and display each customer's details
                while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getString("ID") %></td>
                <td><%= rs.getString("NAME") %></td>
                <td><%= rs.getInt("PHONE") %></td>
                <td><%= rs.getString("EMAIL") %></td>
                <td><%= rs.getString("USERNAME") %></td>
                <td><%= rs.getInt("AGE") %></td>
                <td><%= rs.getString("ADDRESS") %></td>
            </tr>
            <% 
                }
                // Close the ResultSet to release resources
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            %>
        </tbody>
    </table>
    
    <a href="Emp_Profile.jsp" class="back-btn">Back</a>

    <div class="footer">
        <%@include file="HeaderNFooter/Footer.jsp" %>
    </div>
</body>
</html>
