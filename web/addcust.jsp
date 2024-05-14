<%-- 
    Document   : addcust
    Created on : May 1, 2024, 2:29:23 PM
    Author     : ROG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="da.customerDA"%>
<%@page import="servlet.register"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <link href="CSS/All.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/managerCss.css" rel="stylesheet" type="text/css"/>
         <style>
       .container {
            margin: 20px auto;
            width: 50%;
            border: 1px solid #ccc;
            padding: 30px;
            border-radius: 10px; 
            background-color: #f9f9f9;
        }
        body{
            background-color: lightcyan;
        }

        h1 {
            text-align: center;
        }

        form {
            margin-top: 20px;
        }

        form p {
            margin-bottom: 15px;
        }

        label {
            display: inline-block;
            width: 120px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="password"],
        select {
            width: 300px;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
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

        .error {
            color: red;
            font-size: 14px;
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
        <h1>Registration</h1>
        <%
    // Generate new Employee ID and store it in a variable
    customerDA registerDA = new customerDA();
    String csID = registerDA.generateNewCustomerID(); 
    %>
        <div class="container">
        <form method="post" action="addCust" >

            <p><label>Customer ID</label>
                <input type="text" name="id" size="10" value="<%= csID %>" readonly /></p>
            
            <p><label>Name</label>
                <input type="text" name="name" size="15"  value="${after.name}"/></p>
            <span name="errorName" style="color: red">${errors.name}</span>
            
            <p><label>Phone Number</label>
                <input type="text" name="phone" size="15"  value="${after.phone}"/></p>
            <span name="errorphone" style="color: red">${errors.phone}</span>
            
            <p><label>Username</label>
                <input type="text" name="username" size="40"  value="${after.username}"/></p>
            <span name="errorusername" style="color: red">${errors.username}</span>

            <p><label>Password</label>
                <input type="password" name="password"  size="40" ${after.password}/></p>
            <span name="errorpassword" style="color: red">${errors.password}</span>
            
            
            
            <p><label>Email</label>
                <input type="text" name="email" size="40"  value="${after.email}"/></p>
            <span name="erroremail" style="color: red">${errors.email}</span>

            <p><label>Age</label>
                <select name="age">
            <%
                String[] ageOptions = registerDA.generateAgeOptions();
                for (String option : ageOptions) {
                    out.println("<option value=\"" + option + "\">" + option + "</option>");
                }
            %>
        </select>
            
            <p><label>Address</label>
                <input type="text" name="address" size="100"  value="${after.address}"/></p>
            <span name="erroraddress" style="color: red">${errors.address}</span>
            
            <p><input type="submit" value="Submit" />
                <input type="reset" value="Reset" /></p>
            <div style=" margin-top: 20px;">
                <a href="manager_maintain_cust.jsp" class="back-button">Back</a>
            </div>
        </form>
        </div>
    </body>
</html>
