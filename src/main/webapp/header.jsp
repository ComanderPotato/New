<%--
  Created by IntelliJ IDEA.
  User: Tom
  Date: 18/05/2023
  Time: 8:32 am
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="iotbay.model.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="iotbay.model.dao.UserAccountDBManager" %>
<%@ page import="iotbay.model.dao.ProductDBManager" %>
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
            <%

                ArrayList<CartItem> cartItems = (ArrayList<CartItem>) session.getAttribute("cartItems");
                ProductDBManager productManager = (ProductDBManager) session.getAttribute("productManager");
            %>
            <div class="dropdown-content dropdown-cart<%
                if (cartItems != null && cartItems.size() >= 5) {
                    out.print(" scrollable");
                }
            %>">
                <% if (cartItems == null || cartItems.isEmpty()) { %>
                <a href="#">Cart empty: add items</a>
                <% } else {
                    for (CartItem cartItem : cartItems) {
                        Product product = productManager.getProduct(cartItem.getProductID());
                %>
                <form action="RemoveItemFromCartController" method="post" class="cart-item">
                    <div class="product-details">
                        <h4 class="product-name"><%= product.getName() %></h4>
                        <p class="product-price">$<%= product.getPrice() %></p>
                        <p class="product-quantity">Quantity: <%= cartItem.getQuantity() %></p>
                    </div>
                    <input type="hidden" name="productID" value="<%=product.getID()%>">
                    <input type="hidden" name="productQuantity" value="<%=product.getQuantity()%>">
                    <input type="hidden" name="cartItemID" value="<%=cartItem.getID()%>">
                    <input type="hidden" name="cartItemQuantity" value="<%=cartItem.getQuantity()%>">
                    <button type="submit" class="remove-button">Remove</button>
                </form>
                <% }
                %>
                <a href="#">Create Order</a>
                <%
                    } %>
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
