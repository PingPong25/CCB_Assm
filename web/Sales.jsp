<%-- 
    Document   : Sales
    Created on : 13 May 2024, 2:03:36 am
    Author     : User
--%>

<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.List"%>
<%@page import="da.ProductDA"%>
<%@page import="domain.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%    
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String sql = "SELECT SID, PID, QUANTITY, REVENUE, DAY, MONTH, YEARS, DATE FROM SALES";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CCB", "nbuser", "nbuser");
            pstmt = conn.prepareStatement(sql);
            //pstmt.setString(1, cs1.getId());
            rs = pstmt.executeQuery();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Sales Report</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <link href="CSS/All.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/managerCss.css" rel="stylesheet" type="text/css"/>
    <style>
        /* Align comment form elements */
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
    }
    
    /* Table styles */
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th, td {
        padding: 10px;
        border: 1px solid #ccc;
    }

    th {
        background-color: #f2f2f2;
        font-weight: bold;
        text-align: center;
    }

    td {
        text-align: left;
    }

    /* Product image styles */
    .product-image {
        width: 100px;
        height: auto;
    }

    /* Add to cart button styles */
    .add-to-cart-btn {
        background-color: #4CAF50;
        color: white;
        padding: 8px 16px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        border-radius: 4px;
    }

    .add-to-cart-btn:hover {
        background-color: #45a049;
    }

    /* Additional styles for the comment section */
    #comments {
        margin-top: 20px;
    }

    #comments div {
        border: 1px solid #ccc;
        padding: 10px;
        margin-bottom: 10px;
    }

    #comments p {
        margin: 5px 0;
    }

    #commentText {
        width: calc(100% - 160px);
        margin-right: 10px;
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 8px;
        font-size: 16px;
        line-height: 1.6;
    }

    #commentForm input[type="submit"] {
        width: 150px;
        padding: 12px;
        border: none;
        border-radius: 8px;
        background-color: #4CAF50;
        color: white;
        font-size: 16px;
        cursor: pointer;
    }

    #commentForm input[type="submit"]:hover {
        background-color: #45a049;
    }


        
        .submit-comment form {
            display: flex;
            align-items: flex-start;
            justify-content: center;
            flex-wrap: wrap;
        }

        .submit-comment input[type="text"] {
            flex: 1;
            width: calc(100% - 180px);
            margin-right: 10px;
            margin-bottom: 10px;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 8px;
            background-color: #f8f8f8;
            font-size: 16px;
            line-height: 1.6;
        }

        .submit-comment input[type="submit"] {
            flex: 0 0 auto;
            width: 150px;
            margin-bottom: 10px;
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
    </style>
</head>
<body>
 <%@include file="HeaderNFooter/HeaderAfterLogin_Manager.jsp" %>

 <h1 style="text-align: center;">Sales Report</h1>
 <form action="SalesRecord" method="GET">
    <label for="day">Day:</label>
    <input type="text" id="day" name="day">

    <label for="month">Month:</label>
    <input type="text" id="month" name="month">

    <label for="year">Year:</label>
    <input type="text" id="year" name="year">

    <input type="submit" value="Search">
</form>
<table border="1" style="text-align: center;">
    <tr>
        <th>Sales ID</th>
        <th>Product ID</th>
        <th>Quantity</th>
        <th>Revenue</th>
        <th>Day</th>
        <th>Month</th>
        <th>Years</th>
        <th>Date</th>
    </tr>
    <%
        while (rs.next()) {
            String SID = rs.getString(1);
            String PID = rs.getString(2);
            int quantity = rs.getInt(3);
            double revenue = rs.getDouble(4);
            int day = rs.getInt(5);
            int month = rs.getInt(6);
            int years = rs.getInt(7);
            Timestamp date = rs.getTimestamp(8);
    %>
    <tr>
        <td><%=SID%></td>
        <td><%=PID%></td>
        <td><%=quantity%></td>
        <td><%=revenue%></td>
        <td><%=day%></td>
        <td><%=month%></td>
        <td><%=years%></td>
        <td><%=date%></td>
    </tr>
    <% } %>
</table>
<%
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
%>
<div class="footer">
        <%@ include file= "HeaderNFooter/Footer.jsp"%>
    </div>
</body>
</html>