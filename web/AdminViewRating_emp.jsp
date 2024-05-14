<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="CSS/All.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/managerCss.css" rel="stylesheet" type="text/css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rating Display</title>
    <style>
        
        /* Rating value styles */
        .rating {
            font-weight: bold;
            color: #007bff; /* Blue color for rating value */
        }

        /* Created at time styles */
        .time {
            color: #6c757d; /* Gray color for timestamp */
            font-size: 0.8em;
        }
        
        /* Styles for the rating display section */
        .rating-display {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin: 20px auto;
            max-width: 600px;
        }
        
        .rating-list {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }
        
        .rating-item {
            margin-bottom: 10px;
        }
        
        .rating-item span {
            font-weight: bold;
            color: #007bff;
            margin-right: 5px;
        }
        
        .average-rating {
            margin-top: 20px;
            text-align: center;
            font-size: 1.2em;
        }
    </style>
</head>
<body>
    <%@include file="HeaderNFooter/HeaderAfterLogin_Emp.jsp" %>
     
     <div class="container">
        <div class="sidebar-box">
            <div class="sidebar">
                <h2>Welcome</h2>
                <ul>
                    <li>
                        <a href="Emp_Profile.jsp"><b>Profile</b></a>
                    </li>
                    <li class="dropdownBox">
                        <a href=""><b>Manage</b></a>
                        <ul class="dropdownContent">
                            <li><a href="emp_view_cust.jsp">View Customers</a></li>
                            <li><a href="emp_maintain_product.jsp">Product</a></li>
                            <li><a href="AdminReply_emp.jsp">Comment</a></li>
                            <li><a href="AdminViewRating_emp.jsp">Rating</a></li>
                        </ul>
                    </li> 
                    <li>
                        <a href="mainPage.jsp"><b>Logout</b></a>
                    </li>
                </ul>
            </div>
        </div>
      
        <div class="content"> 
            <div class="rating-display">
                <h1 style="margin-bottom: 20px; color: #007bff; text-align: center;">Rating Overview</h1>
                <ul class="rating-list">
                    <%
                        Connection conn = null;
                        PreparedStatement pstmt = null;
                        ResultSet rs = null;
                        double totalSum = 0;
                        int totalRatings = 0;
                        
                        try {
                            // Establish database connection
                            String url = "jdbc:derby://localhost:1527/CCB";
                            String user = "nbuser";
                            String password = "nbuser";
                            conn = DriverManager.getConnection(url, user, password);
                            
                            // Create SQL query to retrieve ratings from database
                            String sql = "SELECT rating FROM ratings";
                            pstmt = conn.prepareStatement(sql);
                            
                            // Execute SQL query
                            rs = pstmt.executeQuery();
                            
                            // Display ratings
                            while (rs.next()) {
                                int rating = rs.getInt("rating");
                    %>
                                <li class="rating-item">Rating: <span class="rating"><%= rating %></span></li>
                    <%
                                // Calculate total sum of ratings
                                totalSum += rating;
                                // Increment total number of ratings
                                totalRatings++;
                            }
                        } catch (SQLException e) {
                            out.println("Error retrieving ratings: " + e.getMessage());
                        } finally {
                            // Close database resources
                            try {
                                if (rs != null) rs.close();
                                if (pstmt != null) pstmt.close();
                                if (conn != null) conn.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        
                        // Calculate average rating
                        double averageRating = totalRatings > 0 ? totalSum / totalRatings : 0;
                        // Format average rating to two decimal places
                        String formattedAverageRating = String.format("%.2f", averageRating);
                    %>
                </ul>
                <p class="average-rating">Average Rating: <%= formattedAverageRating %></p>
            </div>
        </div>
    
        <div class="sidebar">
            <!-- Sidebar content -->
        </div>
    </div>
    
    <div class="footer">
        <%@include file= "HeaderNFooter/Footer.jsp"%>
    </div>
</body>
</html>
