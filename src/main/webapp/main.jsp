<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="iotbay.model.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="iotbay.model.dao.UserAccountDBManager" %>
<%@ page import="iotbay.model.dao.ProductDBManager" %>
<%@ page import="iotbay.model.dao.ProductCategoryDBManager" %>
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
        .main-container {
            margin: 0 auto;
            max-width: 1200px;
            padding: 20px 0;

        }
        .grid-container {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 20px;
            padding: 20px 0;
            /*justify-items: center;*/
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
        .button-grn {
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 3px;
            border: none;
            background-color: #4CAF50;
            color: #fff;
            cursor: pointer;
        }

        .button-grn:hover {
            background-color: #45a049;
        }
        .button-red {
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 3px;
            border: none;
            background-color: #af4c4c;
            color: #fff;
            cursor: pointer;
        }

        .button-red:hover {
            background-color: #a04545;
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
        .dropdown-cart {
            position: absolute;
            background-color: #f9f9f9;
            min-width: 200px;
            box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            padding: 10px;
            z-index: 1;
        }

        .cart-item {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 10px;
            color: black;
        }

        .product-details {
            flex-grow: 1;
        }

        .product-name {
            margin: 0;
            font-weight: bold;
        }

        .product-price {
            margin: 0;
        }

        .product-quantity {
            margin: 0;
            font-style: italic;
        }

        .remove-button {
            background-color: #f44336;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 3px;
            cursor: pointer;
        }

        .remove-button:hover {
            background-color: #d32f2f;
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
        .scrollable {
            max-height: 200px; /* Adjust the desired maximum height */
            overflow-y: auto;
        }
        .btn-dropdown {
            padding: 4px 8px;
        }
        .form-btns {
            margin-bottom: 10px;
        }
        .search-bar {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 20px;
        }

        .search-input {
            width: 200px;
            padding: 5px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .sort-select {
            width: 150px;
            padding: 5px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .filter-select {
            width: 150px;
            padding: 5px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp" />
<%
    ArrayList<Product> productList = (ArrayList<Product>) session.getAttribute("products");
    ProductCategoryDBManager productCategoryManager = (ProductCategoryDBManager) session.getAttribute("productCategoryManager");
%>
<div class="main-container">
<div class="search-bar" id="search-form">
    <input type="text" class="search-input" placeholder="Search...">
    <select class="sort-select">
        <option value="name">Sort by Name</option>
        <option value="price">Sort by Price</option>
        <option value="category">Sort by Category</option>
    </select>

    <select class="filter-select">
        <option value="">All Categories</option>
        <%
            ArrayList<ProductCategory> productCategories = productCategoryManager.fetchCategories();
            if(!productCategories.isEmpty()) {
                for(ProductCategory category : productCategories) {
        %>
            <option value="category"><%=category.getCategory()%></option>
        <%
                }
            }
        %>
    </select>
</div>
<div class="grid-container">
    <%
        for (Product product : productList) {
            String productCategory = productCategoryManager.getCategory(product.getID());
    %>
<%--    <div class="product-card">--%>

    <form action="AddItemController" method="post" class="product-card">

        <img src="<%= product.getImage()%>" alt="Product Image">

        <h3><%= product.getName()%></h3>
        <p><%= product.getDescription()%></p>
        <p>Category: <%= productCategory %></p>
        <p class="price">$<%= product.getPrice()%></p>
        <p class="quantity">In Stock: <%= product.getQuantity()%></p>

        <div class="card-btn">
            <input type="number" id="quantity" name="quantity">
            <input type="hidden" name="selectedProductID" value="<%=product.getID()%>">
            <input type="hidden" name="selectedProductPrice" value="<%=product.getPrice()%>">
            <button type="submit" class="button-grn">Add to cart</button>
        </div>
    </form>
<%--    </div>--%>

    <% } %>

</div>
</div>
<script>
    const form = document.getElementById('search-form');
    const searchInput = document.querySelector('.search-input');
    const sortSelect = document.querySelector('.sort-select');
    const filterSelect = document.querySelector('.filter-select');

    function handleFormChange(event) {
        event.preventDefault(); // Prevent the default form submission
        console.log(event.target)
        const xhr = new XMLHttpRequest();
        xhr.open(
            'GET',
            '/SortProductsServlet?search=' +
            encodeURIComponent(searchInput.value) +
            '&sort=' +
            encodeURIComponent(sortSelect.value) +
            '&filter=' +
            encodeURIComponent(filterSelect.value),
            true
        );
        xhr.send();
    }

    // Add event listener to the form
    form.addEventListener('submit', handleFormChange);
    searchInput.addEventListener('input', handleFormChange);
    sortSelect.addEventListener('change', handleFormChange);
    filterSelect.addEventListener('change', handleFormChange);
</script>
</body>
</html>



