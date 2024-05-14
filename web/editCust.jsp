<%@ page import="da.customerDA, domain.customer" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Edit Customer</title>
    <link href="CSS/All.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/managerCss.css" rel="stylesheet" type="text/css"/>
     <style>
       body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        .container {
            margin: 20px auto;
            width: 60%;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 10px; 
            padding: 30px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
        }

        form {
            margin-top: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: inline-block;
            width: 120px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="password"],
        select {
            width: calc(100% - 130px); /* Adjusted width */
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box; /* Ensures padding is included in the width */
        }

        input[type="submit"],
        input[type="reset"] {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }

        input[type="submit"]:hover,
        input[type="reset"]:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: red;
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
        customerDA da = new customerDA();
        customer cust = da.getCustomerById(id);
        if(cust != null) {
    %>
    <%@include file="HeaderNFooter/HeaderAfterLogin_Manager.jsp" %>
    <div class="container">
        
        <form action="updateCust" method="post">
            <input type="hidden" name="id" value="<%= id %>"><br>
            <div class="form-group">
                <label>ID:</label>
                <%= id %>
            </div>
            
            <div class="form-group">
                <label>Username:</label>
                <%= cust.getUsername() %><br>
            </div>
            
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" name="name" id="name" value="<%= cust.getName() %>" ><br>
                <span name="error-message" style="color: red">${nameError}</span>
            </div>
            
            <div class="form-group">
                <label>Phone:</label>
                <%= cust.getPhone() %><br>
            </div>
            
            <div class="form-group">
                <label>Email:</label>
                <%= cust.getEmail() %><br>
            </div>
            
            <div class="form-group">
                <label for="age">Age:</label>
                <input type="text" name="age" id="age" value="<%= cust.getAge() %>" ><br>
                <span name="error-message" style="color: red">${ageError}</span>
            </div>
          
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" name="address" id="address" value="<%= cust.getAddress() %>" ><br>
                <span name="error-message" style="color: red">${addressError}</span>
            </div>

            <input type="submit" value="Save Changes" id="sub"><br>
            <div style=" margin-top: 20px;">
                <a href="manager_maintain_cust.jsp" class="back-button">Back</a>
            </div>
        </form>
    </div>
    <% } else { %>
        <%@include file="customer_not_found.jsp" %>
    <% } %>
    <div class="footer">
        <%@include file="HeaderNFooter/Footer.jsp" %>
    </div>
</body>
</html>
