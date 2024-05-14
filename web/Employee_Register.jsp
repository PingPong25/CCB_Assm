<%-- 
    Document   : Employee_Register
    Created on : Apr 30, 2024, 10:40:27 PM
    Author     : Adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="da.EmployeeDA"%>
<%@page import="servlet.EmployeeRegisterServlet"%>

<!DOCTYPE html> 
<html>
    <head>
        <title>Employee Register</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/All.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/managerCss.css" rel="stylesheet" type="text/css"/>
         <style>
       .container {
            margin: 20px auto;
            width: 30%;
            border: 1px solid #ccc;
            padding: 30px;
            border-radius: 10px; 
            background-color: #f9f9f9;
        }
             
        .footer {
            position: fixed;
            bottom: 0;
            width: 100%;
            height: 50px; 
            background-color: #f5f5f5; 
            text-align: center;
            line-height: 50px;
        }
        
        .blue-button {
            background-color: #007bff; /* Blue color */
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
        }

        .blue-button:hover {
            background-color: #0056b3; /* Darker blue color on hover */
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
       <%@include file="HeaderNFooter/HeaderAfterLogin_Manager.jsp" %>
       <h3 style="text-align:center;">New Employee Details</h3>
        <div class="container">
    

    <%
        // Generate new Employee ID and store it in a variable
        EmployeeDA registerDA = new EmployeeDA();
        String empId = registerDA.generateNewEmployeeID(); 
    %>

    <form action="EmployeeRegisterServlet" method="POST">
        <p><label>Employee ID</label><br>
            <input type="text" name="empId" size="10" value="<%= empId%>" readonly/></p>
        <p><label>Employee Name</label>
            <input type="text" name="empName" size="12" value="${after.name}"/></p>
        <span name="employeeName" style="color: red">${errors.name}</span>
        <p><label>Phone Number</label>
            <input type="text" name="empPhone" size="12" value="${after.phone}"/></p> 
        <span name="employeeName" style="color: red">${errors.phone}</span>
        <p><label>Email</label> <br>
            <input type="text" name="email" size="12" value="${after.email}"/></p>
        <span name="employeeName" style="color: red">${errors.email}</span>
        <p><label>UserName</label> <br>
            <input type="text" name="userName" size="10" value="<%= empId%>" readonly/></p>
        <p><label>Age</label>
            <select name="age">
                <%
                    String[] ageOptions = registerDA.generateAgeOptions();
                    for (String option : ageOptions) {
                        out.println("<option value=\"" + option + "\">" + option + "</option>");
                    }
                %>
            </select>
        </p>
        <p><label>Gender</label>
            <input type="radio" name="gender" value="F" />Female
            <input type="radio" name="gender" value="M" />Male</p>
        <span name="employeeName" style="color: red">${errors.gender}</span>
        <p><label>Job</label>
            <input type="text" name="job" size="20" value="${after.job}"/></p>
        <span name="employeeName" style="color: red">${errors.job}</span>
        
        <p>
            <input type="submit" value="Submit" class="blue-button"/>
            <input type="reset" value="Reset" class="blue-button"/>
             <div style=" margin-top: 20px;">
                <a href="manager_maintain_emp.jsp" class="back-button">Back</a>
            </div>
        </p>
       
    </form>
</div>
        <div class="footer">
            <%@include file="HeaderNFooter/Footer.jsp" %>
        </div>

    </body>
</html>
