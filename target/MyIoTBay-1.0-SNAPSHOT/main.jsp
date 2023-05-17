<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="iotbay.model.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="iotbay.model.dao.UserAccountDBManager" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .navbar {
            background-color: #333;
            color: #fff;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
        }

        .navbar-left {
            flex-grow: 1;
        }

        .navbar-logo {
            font-size: 20px;
            font-weight: bold;
            text-decoration: none;
            color: #fff;
        }

        .navbar-center {
            flex-grow: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 20px;
        }


        .navbar-link {
            color: #fff;
            text-decoration: none;
        }

        .navbar-right {
            display: flex;
            gap: 10px;
        }

        .navbar-icon {
            display: inline-block;
            margin-left: 10px;
        }

        .icon {
            width: 24px;
            height: 24px;
            vertical-align: middle;
        }

        .grid-container {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 20px;
            padding: 20px;
            margin: 0 auto;
            max-width: 1200px;
            justify-items: center;
        }

        .product-card {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            max-width: 250px;
            /*display: flex;*/
            /*flex-direction: column;*/
            /*justify-content: space-between;*/
            /*height: 100%;*/
            /*margin: 20px;*/
        }

        .product-card img {
            width: 100%;
            height: 200px;
            object-fit: cover;
            border-radius: 5px;
            margin-bottom: 10px;
        }

        .product-card h3 {
            margin-bottom: 5px;
        }

        .product-card p {
            margin-bottom: 10px;
        }

        .product-card .price {
            font-weight: bold;
        }

        .product-card .quantity {
            font-style: italic;
        }
        .card-btn {
            margin-top: auto;

        }
        .card-btn button {
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 3px;
            border: none;
            background-color: #4CAF50;
            color: #fff;
            cursor: pointer;
        }

        .card-btn button:hover {
            background-color: #45a049;
        }
        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            z-index: 1;
            top: 23px;
            right: 12px;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
            padding: 12px 16px;
            border-radius: 3px;
        }

        .dropdown:hover .dropdown-content,
        .dropdown-content:hover {
            display: block;
        }
        .dropdown-content a {
            display: block;
            padding: 8px 16px;
            text-decoration: none;
            color: #333;
            transition: background-color 0.3s ease;
            border-radius: 4px;
            margin-bottom: 2px;
        }

        .dropdown-content a:hover {
            background-color: #f2f2f2;
        }
        .logout {
            background-color: #f5cbcb;
        }
        .logout:hover {
            background-color: #ffd5d5;
        }

        .login {
            background-color: #cbf5d2;
        }
        .login:hover {
            background-color: #cbf5d2;
        }
        .signup {
            background-color: #cbf5e8;
        }
        .signup:hover {
            background-color: #cbf5e8;
        }
    </style>
</head>
<body>
<nav class="navbar">
    <div class="navbar-left">
        <a class="navbar-logo" href="#">IoTBay</a>
    </div>
    <div class="navbar-center">
        <a class="navbar-link" href="#">Home</a>
        <a class="navbar-link" href="#">Products</a>
        <a class="navbar-link" href="#">Orders</a>
        <a class="navbar-link" href="#">Device Collection</a>
    </div>
    <div class="navbar-right">
        <div class="dropdown">
            <a class="navbar-icon" href="#"><img src="./images/cart.svg" class="icon" alt="Shopping Cart"></a>
            <div class="dropdown-content">
                <!-- Dropdown content here -->
                <a href="#">Dropdown Item 1</a>
                <a href="#">Dropdown Item 2</a>
            </div>
        </div>
        <div class="dropdown">
            <a class="navbar-icon" href="#"><img src="./images/account.svg" class="icon" alt="User Account"></a>
            <div class="dropdown-content">
                <!-- Dropdown content here -->
                <%
                    boolean isLoggedIn = (boolean) session.getAttribute("isLoggedIn");
                    if(isLoggedIn) {
                %>
                <a href="#">Account</a>
                <a class="logout"href="LogoutController">Logout</a>
                <% }  else {%>
                <a class="login" href="LogoutController">Login</a>
                <a class="signup" href="register.jsp">Signup</a>
                <% } %>
            </div>
        </div>
    </div>

</nav>
<%
//    UserAccount name = (UserAccount) session.getAttribute("user");
    ArrayList<Product> productList = (ArrayList<Product>) session.getAttribute("products");
%>

<div class="grid-container">
    <%
        for (Product product : productList) {
    %>
<%--    <div class="product-card">--%>
    <form action="AddItemController" method="post" class="product-card">

        <img src="<%= product.getImage()%>" alt="Product Image">

        <h3><%= product.getName()%></h3>
        <p><%= product.getDescription()%></p>
        <p>Category: <%= product.getCategory()%></p>
        <p class="price">$<%= product.getPrice()%></p>
        <p class="quantity">In Stock: <%= product.getQuantity()%></p>

        <div class="card-btn">
            <input type="number" id="quantity" name="quantity">
            <button type="submit"><a href="AddItemController?selectedProduct=<%=product%>"></a>Add to cart</button>
        </div>
    </form>
<%--    </div>--%>

    <% } %>

</div>
<script>
    function addToCart(productID) {
        <%

        %>
    }
</script>
</body>
</html>



