<%-- 
    Document   : HeaderAfterLogin_Cust
    Created on : 9 May 2024, 3:19:23 pm
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import ="javax.servlet.RequestDispatcher"%>
<%@page import="javax.servlet.http.HttpSession;"%>
<%@page import="java.sql.*"%>
<%@page import="da.customerDA"%>
<%@page import="domain.customer"%> 
<%@page import="servlet.login"%> 
<%@page import="servlet.mainPageServlet"%> 
<!DOCTYPE html>
<html>
    <head>
        <title>Header</title>
        <style>
            header {
                background-color: lightcyan;
                color: black;
                padding: 10px;
                text-align: center;
            }
        </style>
    </head>
    
        
    <body>
        <%
            
            
            // Retrieve the Customer object from the session
            customer cs = (customer) session.getAttribute("customer");
            //ResultSet rs = csDA.selectRecord(logins);
        %>

        <header> 
            <div>
                <a href="mainPage.jsp">
                    <img src="photo/logo.png" alt="LOGO" width="150" height="150" 
                         style="margin-right: 100%; margin-bottom: -120px; mix-blend-mode: darken;">
                </a>
                <h1>TARUMT Univerisity</h1>
            </div>

            

            <br>

            <nav class="navbox">
                <ul style="background-color: lightskyblue; border-radius: 10px;">
                    <li><a href="mainpage_cust.jsp">🏠 Home</a></li>
                    <li>
                        <a>Products</a>
                        <ul>
                            <li><a href="Product_cust.jsp">All Products</a></li>
                        </ul> 
                    </li>
                    <li><a href="Cart_Cust.jsp">🛒 Cart</a></li>
                    <li><a href ="PaymentRecord.jsp">View Order</a></li>
                    <li><a style="font-size: 20px; text-align: center">🧑🏻‍💼
                            <form method="post" action="mainPageServlet">
                            <input type="hidden" name="username" value="<%= cs.getUsername() %>">
                            <input type="hidden" name="password" value="<%= cs.getPassword() %>">
                            <input type="hidden" name="name" value="<%= cs.getName() %>">
                            <input type="hidden" name="id" value="<%= cs.getId() %>">
                            <input type="hidden" name="email" value="<%= cs.getEmail()%>">
                            <input type="hidden" name="phone" value="<%= cs.getPhone()%>">
                            <input type="hidden" name="address" value="<%= cs.getAddress()%>">
                            <input type="hidden" name="age" value="<%= cs.getAge()%>">
                            
                            <button type="submit">
                                <%= cs.getName() %>
                            </button>
                          
                            </form></a>   
                        <ul>
                            <% if(cs.getName()==null){ %>
                            <li><a href ="l.jsp">Login</a></li>
                            <% }else{ %>
                            <li><a href ="mainPage.jsp">Log out</a></li>

                            <% } %>
                        </ul>
                    </li> 
                </ul>        
            </nav>


        </header>
    </body>
</html>

