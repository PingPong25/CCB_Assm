<%-- 
    Document   : mainPage
    Created on : 25 Mar 2024, 10:58:53 pm
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TARUMT Univerisity</title>
        <link href="CSS/All.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <%@include file="HeaderNFooter/Hearder.jsp" %>  
    <div class="container">
        <div class="sidebar">
            <h2>About Us</h2>
            <p>
               Welcome to <b style="font-family: Lucida Console, Courier New, monospace">TARUMT University!</b>
               <br><br>As graduation season approaches, we're your one-stop destination for all things celebratory and commemorative. 
               <br><br>From elegant regalia to personalized keepsakes, we have everything you need to make your graduation experience unforgettable. 
               <br><br>With a dedicated team and a commitment to excellence, we're here to ensure your special day is as seamless and memorable as possible. 
            </p>     
        </div>
        <div class="content">
            <!-- Main content -->
            <!-- Need put Welcome -->
            <h2>Featured Products</h2>
            <!-- Display featured products here -->
            <a href="l.jsp">
            <img src="photo/bag.jpg" alt="Bag" style="width: 30%; height:30%;">
            </a>
                
            <a href="l.jsp">
            <img src="photo/book.jpg" alt="Book" style="width: 30%; height:30%; margin-left: 20%;">
            </a>
            <br><br>
            <br><br>
            <br><br>
            <a href="l.jsp">
            <img src="photo/softbear.jpeg" alt="softtoy" style="width: 30%; height:30%;">
            </a>
            
            <a href="l.jsp">
            <img src="photo/cap.jpg" alt="cap" style="width: 30%; height:30%; margin-left: 20%;">
            </a>
            
            
        </div>
        
        <div class="sidebar">
             <h2>Congratulations!!!</h2>
            <p>
               On reaching this milestone, and let us help you celebrate in style at TARUMT University's Graduation Emporium.
            </p>
        </div>
        
    </div>
    <footer> 
        <%@include file= "HeaderNFooter/Footer.jsp"%>
    </footer>
   
</body>
</html>
