<%-- 
    Document   : ViewEmp
    Created on : 9 May 2024, 4:54:19 pm
    Author     : User
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="da.EmployeeDA"%>
<%@page import="domain.Employee"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Employee Details</title>
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
    <%@include file="HeaderNFooter/HeaderAfterLogin_Manager.jsp" %>
    <h1 style="text-align: center;">Employee Details</h1>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Username</th>
                <th>Age</th>
                <th>Gender</th>
                <th>Job</th>
            </tr>
        </thead>
        <tbody>
            <% 
            try {
                EmployeeDA daEmp = new EmployeeDA();
                // Retrieve all customer records
                ResultSet rs = daEmp.getAllEmpRecords();
                // Loop through the ResultSet and display each customer's details
                while (rs.next()) {
            %> 
            <tr>
                <td><%= rs.getString("EMPLOYEE_ID") %></td>
                <td><%= rs.getString("EMPLOYEE_NAME") %></td>
                <td><%= rs.getString("PHONE_NUM") %></td>
                <td><%= rs.getString("EMAIL") %></td>
                <td><%= rs.getString("USERNAME") %></td>
                <td><%= rs.getInt("AGE") %></td>
                <td><%= rs.getString("GENDER") %></td>
                <td><%= rs.getString("JOB") %></td>   
            </tr>
            <% 
                }
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            %>
        </tbody>
    </table>
    
    <a href="manager_maintain_emp.jsp" class="back-btn">Back</a>

    <div class="footer">
        <%@include file="HeaderNFooter/Footer.jsp" %>
    </div>
</body>
</html>
