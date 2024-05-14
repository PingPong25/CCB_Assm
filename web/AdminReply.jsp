<%@page import="java.sql.Blob"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.util.List"%>
<%@page import="domain.Product"%>
<%@page import="da.ProductDA"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String message = ""; // Declare and initialize the message variable

    try {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CCB", "nbuser", "nbuser");
        stmt = conn.createStatement();

%>

<!DOCTYPE html>
<html>
<head>
    <title>Comments</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        .comment {
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 15px;
            padding: 15px;
        }

        .comment p {
            margin: 5px 0;
        }

        .comment form {
            margin-top: 10px;
        }

        .comment input[type="text"],
        .comment input[type="submit"] {
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
            margin-right: 5px;
        }

        .comment input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .comment input[type="submit"]:hover {
            background-color: #0056b3;
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
    <h1 style="text-align: center;">Comments Details</h1>
    <div class="container">
        <!-- Search Bar -->
        <div class="search-container">
        </div>
 
        <h2>Comments</h2>
         <div id="comments">
            <% 


                try {
                    rs = stmt.executeQuery("SELECT * FROM comments ORDER BY created_at DESC");

                    while (rs.next()) {
                        String commentText = rs.getString("comment_text");
                        String adminReply = rs.getString("admin_reply");
            %>
            <div class="comment">
                <p> <%= commentText %></p>
                <% if(adminReply != null && !adminReply.isEmpty()) { %>
                <p><strong>Admin:</strong> <%= adminReply %></p>
                <% } else { %>
                <form action="AdminCommentReply" method="post">
                    <input type="hidden" name="commentText" value="<%= commentText %>">
                    <input type="text" name="adminReply" placeholder="Reply to this comment...">
                    <input type="submit" value="Reply">
                </form>
                <% } %>
            </div>
            <% 
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (stmt != null) stmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            %>
        </div>
        <div style=" margin-top: 20px;">
                <a href="manager_Profile.jsp" class="back-button">Back</a>
            </div>
    </div>
</body>
</html>

<%
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
%>
