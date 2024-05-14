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
    Statement stmt = null;
    ResultSet rs = null;
    String message = ""; // Declare and initialize the message variable

    try {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CCB", "nbuser", "nbuser");
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM PRODUCT");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Cart</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <link href="CSS/All.css" rel="stylesheet" type="text/css"/>
    <link href="CSS/managerCss.css" rel="stylesheet" type="text/css"/>
    <style>
        /* Align comment form elements */
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
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
    </style>
</head>
<body>

 <%@include file="HeaderNFooter/HeaderAfterLogin_Emp.jsp" %>
 <h1 style="text-align: center;">All Products</h1>
<table border="1" style="text-align: center;">
    <tr>
        <th>Product ID</th>
        <th>Product Name</th>
        <th>Product Description</th>
        <th>Product Price</th>
        <th>Product Image</th>
        <th>Add to Cart</th>
    </tr>
    <%
        while (rs.next()) {

            String productId = rs.getString(1);
            String productName = rs.getString(2);
            String productDescription = rs.getString(3);
            double productPrice = rs.getDouble(4);

            // Display the image
            String base64Image = "";
            Blob imageBlob = rs.getBlob(5);
            if (imageBlob != null) {
                byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());
                base64Image = java.util.Base64.getEncoder().encodeToString(imageData);
            }
    %>
    <tr>
        <td><%=productId%></td>
        <td><%=productName%></td>
        <td><%=productDescription%></td>
        <td><%=productPrice%></td>
        <td><img class="product-image" src="data:image/jpeg;base64, <%= base64Image %>" alt="Product Image"></td>
        <td><a class="add-to-cart-btn" href="AddToCart_Emp?PID=<%= productId %>">Add To Cart</a></td>
    </tr>
    <% } %>
</table>
   <%-- Display comments here --%>
        <h2>Comments</h2>
        <div id="comments">
            <% 
                // Retrieve comments from the database and display them
                conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CCB", "nbuser", "nbuser");
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM comments ORDER BY created_at DESC");

                while (rs.next()) {
    String commentText = rs.getString("comment_text");
    String adminReply = rs.getString("admin_reply");
    out.println("<div>");
    out.println("<p>Anonymous: " + commentText + "</p>");
    if (adminReply != null) {
        out.println("<p>Admin: " + adminReply + "</p>");
    }
    out.println("</div>");
}

            %>
        </div>
        <div class="submit-comment">
            <form id="commentForm" action="Feedback_Emp" method="post" onsubmit="return validateCommentForm()">
                <input type="hidden" name="action" value="comment">
                <input type="text" id="commentText" name="commentText" placeholder="Enter your comment..." required>
                <input type="submit" class="submit-comment" value="Submit Comment">
            </form>
        </div>
    </div>

    <script>
        function validateCommentForm() {
            var commentText = document.getElementById("commentText").value.trim();
            if (commentText === "") {
                alert("Please enter your comment.");
                return false;
            }
            return true;
        }
    </script>

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
 <%@include file= "HeaderNFooter/Footer.jsp"%>
</body>
</html>
