package iotbay.controller;

import iotbay.model.*;
import iotbay.model.dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
//@WebServlet(name="RegisterServlet", value="/RegisterServlet")

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        ProductDBManager productManager = (ProductDBManager) session.getAttribute("productManager");
        CartItemManager cartItemManager = (CartItemManager) session.getAttribute("cartItemManager");
        CartDBManager cartManager = (CartDBManager) session.getAttribute("cartManager");
        OrderDBManager orderManager = (OrderDBManager) session.getAttribute("orderManager");
        boolean isLoggedIn = (boolean) session.getAttribute("isLoggedIn");
        Cart cart = (Cart) session.getAttribute("cart");
        UserAccount user;
        Guest guest;
        validator.clear(session);
        ArrayList<CartItem> cartItems;
        ArrayList<Product> products;
        ArrayList<ShopOrder> orders;
        try {
            if(isLoggedIn) {
                user = (UserAccount) session.getAttribute("user");
                orders = orderManager.fetchOrders(user.getID(), "user");
                session.setAttribute("cart", cartManager.getCart(user.getID(), "user"));
            } else {
                guest = (Guest) session.getAttribute("guest");
                orders = orderManager.fetchOrders(guest.getID(), "guest");
                session.setAttribute("cart", cartManager.getCart(guest.getID(), "guest"));

            }
            products = productManager.fetchProducts();
            session.setAttribute("products", products);
            session.setAttribute("orders", orders);
            cartItems = cartItemManager.fetchCartItems(cart.getID());
            session.setAttribute("cartItems", cartItems);
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("main.jsp").include(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        ProductDBManager productManager = (ProductDBManager) session.getAttribute("productManager");
        CartItemManager cartItemManager = (CartItemManager) session.getAttribute("cartItemManager");
        CartDBManager cartManager = (CartDBManager) session.getAttribute("cartManager");
        OrderDBManager orderManager = (OrderDBManager) session.getAttribute("orderManager");
        boolean isLoggedIn = (boolean) session.getAttribute("isLoggedIn");
        Cart cart = (Cart) session.getAttribute("cart");
        UserAccount user = (UserAccount) session.getAttribute("user");
        validator.clear(session);
        ArrayList<CartItem> cartItems;
        ArrayList<Product> products;
        ArrayList<ShopOrder> orders;
        try {
            if(isLoggedIn) {
                orders = orderManager.fetchOrders(user.getID(), "user");
            } else {
                orders = orderManager.fetchOrders(user.getID(), "guest");
            }
            products = productManager.fetchProducts();
            session.setAttribute("products", products);
            session.setAttribute("orders", orders);
            cartItems = cartItemManager.fetchCartItems(cart.getID());
            session.setAttribute("cartItems", cartItems);
            session.setAttribute("cart", cartManager.getCart(user.getID(), "user"));
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);

        }
        request.getRequestDispatcher("main.jsp").include(request, response);
    }
}
