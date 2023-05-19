package iotbay.controller;

import iotbay.model.*;
import iotbay.model.dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
//@WebServlet(name="RegisterServlet", value="/RegisterServlet")

public class OrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        OrderDBManager orderManager = (OrderDBManager) session.getAttribute("orderManager");
        ShippingMethodDBManager shippingMethodManager = (ShippingMethodDBManager) session.getAttribute("shippingMethodManager");

        boolean isLoggedIn = (boolean) session.getAttribute("isLoggedIn");
        UserAccount user;
        Guest guest;
        ArrayList<ShippingMethod> shippingMethods;
        ArrayList<ShopOrder> orders;
        try {
            if(isLoggedIn) {
                user = (UserAccount) session.getAttribute("user");
                orders = orderManager.fetchOrders(user.getID(), "user");
            } else {
                guest = (Guest) session.getAttribute("guest");
                orders = orderManager.fetchOrders(guest.getID(), "guest");
            }
            shippingMethods = shippingMethodManager.getShippingMethods();
            session.setAttribute("shippingMethods", shippingMethods);
            session.setAttribute("orders", orders);
            request.getRequestDispatcher("orders.jsp").include(request, response);
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        OrderDBManager orderManager = (OrderDBManager) session.getAttribute("orderManager");
        ShippingMethodDBManager shippingMethodManager = (ShippingMethodDBManager) session.getAttribute("shippingMethodManager");

        boolean isLoggedIn = (boolean) session.getAttribute("isLoggedIn");
        UserAccount user = (UserAccount) session.getAttribute("user");
        ArrayList<ShippingMethod> shippingMethods;
        ArrayList<ShopOrder> orders;
        try {
            if(isLoggedIn) {
                orders = orderManager.fetchOrders(user.getID(), "user");
            } else {
                orders = new ArrayList<>();
            }
            shippingMethods = shippingMethodManager.getShippingMethods();
            session.setAttribute("shippingMethods", shippingMethods);
            session.setAttribute("orders", orders);
            request.getRequestDispatcher("orders.jsp").include(request, response);
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
