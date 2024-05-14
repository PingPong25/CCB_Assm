<%-- 
    Document   : editEmp
    Created on : 9 May 2024, 5:15:31 pm
    Author     : User
--%>

<%@ page import="da.EmployeeDA, domain.Employee" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Edit Customer</title>
    <link href="CSS/All.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/managerCss.css" rel="stylesheet" type="text/css"/>
    <style>
        .container {
            max-width: 600px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        input#sub[type="text"] {
            width: calc(100% - 10px);
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input#sub[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        input#sub[type="submit"]:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: red;
            margin-top: 5px;
        }
        
        a.back-button {
            display: inline-block;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background-color: #007bff;
            color: #fff; 
            text-decoration: none; 
            cursor: pointer;
        }

        a.back-button:hover {
            background-color: #0056b3; 
        }
    </style>
</head>
<body>   
    <% 
        String id = request.getParameter("id");
        EmployeeDA daEmp = new EmployeeDA();
        Employee emp = daEmp.getCustomerById(id);
        if(emp != null) {
    %>
    <%@include file="HeaderNFooter/HeaderAfterLogin_Manager.jsp" %>
    <div class="container">
        
        <form action="updateEmp" method="post">
            <input type="hidden" name="id" value="<%= id %>">
            <div class="form-group">
                <label>ID:</label>
                <%= id %>
            </div>
            
            <div class="form-group">
                <label>Username:</label>
                <%= emp.getUserName() %>
            </div>
            
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" name="name" id="name" value="<%= emp.getEmpName() %>" >
                 <span name="error-message" style="color: red">${nameError}</span>
            </div>
            
            <div class="form-group">
                <label>Phone:</label>
                <%= emp.getEmpPhone() %>
            </div>
            
            <div class="form-group">
                <label>Email:</label>
                <%= emp.getEmail() %>
            </div>
            
            <div class="form-group">
                <label for="age">Age:</label>
                <input type="text" name="age" id="age" value="<%= emp.getEmpAge() %>">
                <span name="error-message" style="color: red">${ageError}</span>
            </div>
            
            <div class="form-group">
                <label>Gender:</label>
                <%= emp.getEmpGender() %>
            </div>
          
            <div class="form-group">
                <label for="job">Job:</label>
                <input type="text" name="job" id="job" value="<%= emp.getEmpJob() %>" >
                <span name="error-message" style="color: red">${jobError}</span>
            </div>

            <input type="submit" value="Save Changes" id="sub">
            <div style=" margin-top: 20px;">
                <a href="manager_maintain_emp.jsp" class="back-button">Back</a>
            </div>
        </form>
        <% } else { %>
             <%@include file="employee_not_found.jsp" %>
        <% } %>
        
    </div>
       <div class="footer">
        <%@include file="HeaderNFooter/Footer.jsp" %>
        </div>
</body>
</html>

