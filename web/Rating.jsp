<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rating</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        /* Center the rating container */
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        /* Style for rating */
        .rating-section {
            margin-top: 20px;
        }
        .rating-form {
            display: flex;
            align-items: center;
            margin-left: 39%;
        }
        .rating-form label {
            margin-right: 10px;
        }
        .rating-form input[type="radio"] {
            display: none;
        }
        .rating-form label i.fa-star {
            font-size: 20px;
            color: #ddd;
            cursor: pointer;
        }
        .rating-form label i.fa-star:hover,
        .rating-form label input[type="radio"]:checked ~ i.fa-star {
            color: #ffc107;
        }
    </style>
</head>
<body>
    <form action="RatingServlet" method="POST">
    <!-- Rating section -->
    <div class="rating-section">
 <h1 style="text-align: center; font-size: 50px;">Thank you for your purchasing in RON'S Toys</h1>
        <h2 style="text-align: center;">Rate Our Service</h2>
        <div class="rating-form">
            <label for="rating1"><input type="radio" id="rating1" name="rating" value="1"><i class="far fa-star"></i></label>
            <label for="rating2"><input type="radio" id="rating2" name="rating" value="2"><i class="far fa-star"></i></label>
            <label for="rating3"><input type="radio" id="rating3" name="rating" value="3"><i class="far fa-star"></i></label>
            <label for="rating4"><input type="radio" id="rating4" name="rating" value="4"><i class="far fa-star"></i></label>
            <label for="rating5"><input type="radio" id="rating5" name="rating" value="5"><i class="far fa-star"></i></label>
            <button id="submitRating" type="submit">Submit</button>
        </div>
    </div>
    </form>
</body>
</html>
