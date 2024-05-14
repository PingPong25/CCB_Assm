<%-- 
    Document   : profile
    Created on : May 4, 2024, 1:11:38 PM
    Author     : ROG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="javax.servlet.RequestDispatcher"%>
<%@page import="da.customerDA"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="servlet.register"%> 
<%@page import="domain.customer"%> 
<%@page import="servlet.mainPageServlet"%> 
<%@page import="servlet.profileServlet"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <title>Edit Profile</title>
        <style>
    body {
        font-family: Arial, sans-serif;
        
        background-color: lightcyan;
    }

    h1 {
        margin-bottom: 20px;
    }

    #button {
        padding: 10px 20px;
        font-size: 16px;
        border: none;
        border-radius: 5px;
        background-color: #4CAF50;
        color: white;
        cursor: pointer;
        margin: 10px;
    }

    #button:hover {
        background-color: #45a049;
    }

    form {
        display: inline-block;
    }
    
        .profile-box {
        border: 1px solid #ccc;
        padding: 30px;
        margin: 50px auto 0;
        width: 50%;
        border-radius: 10px;
        background-color: #f9f9f9;
        margin-top:10%;
    }
    </style>
    </head>
    <body>
        
        <%
            customerDA csDA = new customerDA();
    
            String username = (String) request.getAttribute("username");
            String id = (String) request.getAttribute("id");
            String name = (String) request.getAttribute("name");
            String phone = (String) request.getAttribute("phone");
            String email = (String) request.getAttribute("email");
            String address = (String) request.getAttribute("address");
            String age = (String) request.getAttribute("age");
    
    
    
    %>
        
        
        <div class="profile-box">
        <form method="post" action="profileServlet" >
            <h1>Edit Profile</h1>
            <p><label>Customer ID</label>
                <% if(id==null) {%>
                <input type="text" name="id" size="10"  value="${after.id}" readonly/></p>  
                <% }else{%>
                <input type="text" name="id" size="10"  value="<%= id %>" readonly/></p>
                <% } %>
        
            <p><label>Name</label>
                <% if(name == null) {%>
                <input type="text" name="name" size="10"  value="${after.name}"/></p>
                <% }else{%>
                <input type="text" name="name" size="15" value="<%= name %>"/></p>
                <% } %>
                <span name="errorName" style="color: red">${error.name}</span>

            
            <p><label>Phone Number</label>
                <% if(phone==null) {%>
                <input type="text" name="phone" size="15" value="${after.phone}" readonly/></p>
                <% }else{%>
                <input type="text" name="phone" size="15" value="<%= phone %>" readonly/></p>
                <% } %>
            
            <p><label>Username</label>
                <% if(username==null) {%>
                <input type="text" name="username" size="15" value="${after.username}" readonly/></p>
                <% }else{%>
                <input type="text" name="username" size="40" value="<%= username %>" readonly/></p>
                <% } %>
        
            <p><label>Email</label>
                <% if(email==null) {%>
                <input type="text" name="email" size="15" value="${after.email}" readonly/></p>
                <% }else{%>
                <input type="text" name="email" size="40" value="<%= email %>" readonly/></p>
                <% } %>
        
            <p><label>Age</label>
                <% if(age==null) {%>
                <input type="number" name="age" size="10"  value="${after.age}"/></p>
                <% }else{%>
                <input type="number" name="age" value="<%= age %>"/></p>
                <% } %>
                <span name="errorAge" style="color: red"/>${error.age}</span>


            
            <p><label>Address</label>
                <% if(age==null) {%>
                <input type="text" name="address" size="10"  value="${after.name}"/></p>
                <% }else{%>
                <input type="text" name="address" size="100" value="<%= address %>"/></p>
                <% } %>
            <span name="errorAddress" style="color: red">${error.address}</span>

            <p><input type="submit" value="Submit" name="button"/>
                <input type="reset" value="Reset" name="button"/></p>
        </form>
        </div>
    </body>
</html>
