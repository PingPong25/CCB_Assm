<%@page import="domain.customer"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="da.PaymentDA"%>
<%@page import="domain.Payment"%>
<%@page import="servlet.PaymentServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="java.util.*" %>
<%
    //generate new payment ID and store it in a variable
    PaymentDA DA = new PaymentDA();
    String paymentID = DA.generateNewPaymentID(); //
    
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);
    
    customer cs = (customer) session.getAttribute("customer");

%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Payment</title>
       <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }
        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #fff;
            margin-top: 10%;
        }
        h1 {
            text-align: center;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"],
        input[type="date"],
        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        select {
            appearance: none;
            -webkit-appearance: none;
            -moz-appearance: none;
            background: url('arrow.png') no-repeat right;
            background-size: 15px;
        }
        .card-fields {
            display: none;
        }
        .btn-submit {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            margin-top: 20px;
        }
        .btn-submit:hover {
            background-color: #45a049;
        }
        .back-btn {
            background-color: #ccc;
            color: #333;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            margin-bottom: 20px;
        }
        .back-btn:hover {
            background-color: #bbb;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Payment</h1>
        <form action="PaymentServlet_Cust" method="POST">
            <div>
                <label>Payment ID:</label>
                <input type="text" name="payID" value="<%=paymentID%>" readonly>
            </div>
            <div>
                <label>User ID</label>
                <input type="text" name="Cid" value="<%=cs.getId()%>" readonly>
                <span style="color: red">${errors.Cid}</span>
            </div>
            <div>
                <label>Name:</label>
                <input type="text" name="CName" value="<%=cs.getName()%>" readonly>
                <span style="color: red">${errors.name}</span>
            </div>
            
            <div>
                <label>Address:</label>
                <input type="text" name="address" value="<%=cs.getAddress()%>" placeholder="30A-06, Lorong Height">
                <span style="color: red">${errors.address}</span>
            </div>
            <div>
                <label>Payment Type:</label>
            </div>
            <div>
                <select name="method" id="paymentOptions" onchange="togglePaymentFields()">
                    <option value="visa">Visa</option>
                    <option value="master">Master</option>
                    <option value="cash">Cash</option>
                </select>
                <span style="color: red">${errors.method}</span>
            </div>
            <div class="card-fields" id="cardFields">
                <div>
                    <label>Name On Card:</label>
                    <input type="text" name="nameOnCard" placeholder="Name On Card">
                    <span style="color: red">${errors.nameOnCard}</span>
                </div>
                <div>
                    <label>Card Number:</label>
                    <input type="text" name="cardNumber" placeholder="xxxx-xxxx-xxxx-xxxx">
                    <span style="color: red">${errors.cardNumber}</span>
                </div>
                <div>
                    <label>Expiry Date:</label>
                    <input type="date" name="expiryDate">
                    <span style="color: red">${errors.expiryDate}</span>
                </div>
                <div>
                    <label>CVV:</label>
                    <input type="text" name="cvv" placeholder="xxx">
                    <span style="color: red">${errors.cvv}</span>

                </div>
            </div>
            <div>
                <label>Total Amount:</label>
                <input type="text" name="totalAmount" value="<%= session.getAttribute("totalAmount") != null ? dcf.format(session.getAttribute("totalAmount")) : "0.00" %>" readonly>
                <span style="color: red">${errors.totalAmount}</span>

            </div>
            <button type="submit" class="btn-submit">Checkout</button><br>
        </form> <br>
            <form action="Cart_Cust.jsp">
            <button type="submit" class="back-btn">Back to Cart</button>
        </form>
    </div>
    <script>
        window.onload = function() {
            var paymentOptions = document.getElementById("paymentOptions");
            paymentOptions.selectedIndex = -1;
        };

        function togglePaymentFields() {
            var paymentOptions = document.getElementById("paymentOptions");
            var cardFields = document.getElementById("cardFields");

            if (paymentOptions.value === "cash") {
                cardFields.style.display = "none";
            } else {
                cardFields.style.display = "block";
            }
        }
    </script>
</body>
</html>
