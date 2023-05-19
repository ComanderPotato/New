<%@ page import="java.util.ArrayList" %>
<%@ page import="iotbay.model.ShopOrder" %>
<%@ page import="iotbay.model.dao.OrderStatusDBManager" %>
<%@ page import="iotbay.model.dao.ShippingMethodDBManager" %>
<%@ page import="iotbay.model.ShippingMethod" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: tomgolding
  Date: 18/5/2023
  Time: 5:21 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Orders</title>
  <style>
    .order-grid {
      display: grid;
      grid-template-columns: repeat(3, 1fr);
      max-width: 1200px;
      gap: 20px;
      padding: 0 0 20px 0;
      margin: 0 auto;
    }
    .order-container {
      max-width: 1200px;
      margin: 0 auto;
    }
    .order-item {
      border: 1px solid #ccc;
      padding: 10px;
    }
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
      position: sticky;
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
    .submit-button {
      background-color: #36f446;
      color: white;
      border: none;
      padding: 5px 10px;
      border-radius: 3px;
      cursor: pointer;
    }

    .dropdown-content a:hover {
      background-color: #f2f2f2;
    }

    .column {
           display: flex;
           justify-content: space-between;
           margin-bottom: 10px;
          align-items: center;
    }
    .order-bar {
      margin-bottom: 20px;
    }
    .button-grn {
      padding: 4px 8px;
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
      padding: 4px 8px;
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
  </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<%
  ArrayList<ShopOrder> orders = (ArrayList<ShopOrder>) session.getAttribute("orders");
  OrderStatusDBManager orderStatusManager = (OrderStatusDBManager) session.getAttribute("orderStatusManager");
  ArrayList<ShippingMethod> shippingMethods = (ArrayList<ShippingMethod>) session.getAttribute("shippingMethods");
  ShippingMethodDBManager shippingMethodManager = (ShippingMethodDBManager) session.getAttribute("shippingMethodManager");
%>
<div class="order-container">
  <h1>Orders</h1>
  <form class="order-bar" id="sort-form" action="SortOrdersController" method="get">
    <select class="sort-select" id="sort-dropdown">
      <option value="">Select a sort</option>
      <option value="SortIDLow">Sort by ID (low to high)</option>
      <option value="SortIDHigh">Sort by ID (high to low)</option>
      <option value="SortPriceLow">Sort by Price (low to high)</option>
      <option value="SortPriceHigh">Sort by Price (high to low)</option>
      <option value="SortDateNew">Sort by Date (new to old)</option>
      <option value="SortDateOld">Sort by Date (old to new)</option>
      <option value="SortStatusLow">Sort by Status (low to high)</option>
      <option value="SortStatusHigh">Sort by Status (high to low)</option>

    </select>
    <input type="hidden" name="sort" id="sort">
    <button type="submit">Sort</button>
  </form>
  <div class="order-grid">
    <%
      if(orders != null) {
      if(!orders.isEmpty()) {
    %>
      <% for (ShopOrder order : orders) {
        String orderStatus = orderStatusManager.getStatus(order.getStatusID());
      %>
    <div class="order-item">
      <div class="column">
        <h3>Order ID:</h3>
        <p><%= order.getID() %></p>
      </div>
      <div class="column">
        <h3>Status ID:</h3>
        <p><%= orderStatus %></p>
      </div>
      <div class="column">
        <h3>Date:</h3>
        <p><%= order.getDateAsString() %></p>
      </div>
      <div class="column">
        <h3>Address: </h3>
        <%
          if(order.getAddressID() == 0) {
        %>
          <button>Add Address</button>
        <% } %>
      </div>
      <div class="column">
        <h3>Payment: </h3>
        <%
          if(order.getPaymentID() == 0) {
        %>
        <button>Add Payment</button>
        <% } %>
      </div>
      <div class="column">
        <h3>Shipping Method:</h3>
        <%
          if(order.getShippingMethodID() == 0) {
        %>
        <select name="shippingDropdown" id="shippingDropdown">
          <option value="">Select method</option>
          <%
            for(ShippingMethod shippingMethod : shippingMethods) {
              String text = shippingMethod.getName() + ": $" + shippingMethod.getPrice();
        %>
          <option value="<%=shippingMethod.getID()%>"><%=text%></option>
        <%
            }
        %>
        </select>
      <% } else {
        ShippingMethod method = shippingMethodManager.getShippingMethod(order.getShippingMethodID());
        String methodText = method.getName() + ": $" + method.getPrice();
      %>
        <p><%=methodText%></p>
        <%
          }
        %>
      </div>
      <div class="column">
        <h3>Total:</h3>
        <p>$<%= order.getTotal() %></p>
      </div>
      <div class="column">
        <form action="CancelOrderController">
          <input type="hidden" name="orderID" value="<%=order.getID()%>">
          <button type="submit" class="button-red">Cancel Order</button>
        </form>
        <form action="SaveOrderController" method="post">
          <input type="hidden" name="orderID" value="<%=order.getID()%>">
          <input type="hidden" name="shippingMethodID" id="shippingMethodID">
          <button type="submit" class="button-grn">Save Order</button>
        </form>
        <form action="SubmitOrderController">
          <input type="hidden" name="orderID" value="<%=order.getID()%>">
          <button type="submit" class="button-grn">Complete Order</button>
        </form>
      </div>
    </div>
       <% } %>
      <% } %>
    <% } else { %>
      <h1>No orders here</h1>
    <% } %>
  </div>
</div>
<script>

  let sortDropdown = document.getElementById("sort-dropdown");
  let sort = document.getElementById("sort");

  sortDropdown.addEventListener("loadstart", changeValue);
  sortDropdown.addEventListener("change", changeValue)
  function changeValue() {
    let orderIndex = sortDropdown.selectedIndex;
    let sortOption = sortDropdown.options[orderIndex];
    sort.value = sortOption.value;
  }
  let shippingDropdown = document.getElementById("shippingDropdown");
  let shippingMethodID = document.getElementById("shippingMethodID");

  shippingDropdown.addEventListener("loadstart", changeMethod);
  shippingDropdown.addEventListener("change", changeMethod)
  function changeMethod() {
    let methodIndex = shippingDropdown.selectedIndex;
    let methodOption = shippingDropdown.options[methodIndex];
    shippingMethodID.value = methodOption.value;
  }
</script>
</body>
</html>
