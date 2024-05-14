<%-- 
    Document   : manager_Profile
    Created on : 7 Apr 2024, 4:53:42 pm
    Author     : User
--%>

<%@page import="da.EmployeeDA"%>
<%@page import="da.customerDA"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate" %>
<%@ page import="ui.CalendarGenerator" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>RON's Toys</title>
    <link href="CSS/All.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/managerCss.css" rel="stylesheet" type="text/css"/>
    <style>
        .content {
            flex-grow: 4;
            padding: 10px;
        }

        .current-day {
            background-color: #007bff;
            color: white;
        }
        
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>

<body>
    <%@include file="HeaderNFooter/HeaderAfterLogin_Manager.jsp" %>

    <div class="container">
        <div class="sidebar-box">
            <div class="sidebar">
                <h2>Welcome Bossku</h2>
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
            <h2>Calendar</h2>
            <% 
                LocalDate currentDate = LocalDate.now();
                int currentYear = currentDate.getYear();
                int currentMonth = currentDate.getMonthValue();
                String calendarHTML = CalendarGenerator.generateCalendarHTML(currentYear, currentMonth);
                out.println(calendarHTML);
            %> 
            
            <h2>Current Customers and Employees</h2>
            <%
                // Create instances of customerDA and EmployeeDA
                customerDA customerDA = new customerDA();
                EmployeeDA employeeDA = new EmployeeDA();

                // Retrieve the number of customers and employees
                int numberOfCustomers = customerDA.getCustomerCount();
                int numberOfEmployees = employeeDA.getEmployeeCount();
            %>
            <table border="1">
                <thead>
                    <tr>
                        <th>Category</th>
                        <th>Number</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Customers</td>
                        <td><%= numberOfCustomers %></td>
                    </tr>
                    <tr>
                        <td>Employees</td>
                        <td><%= numberOfEmployees %></td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="sidebar">
            
        </div>
        
        
    </div>
    
    <div class="footer">
        <%@include file= "HeaderNFooter/Footer.jsp"%>
    </div>
    
</body>
</html>
