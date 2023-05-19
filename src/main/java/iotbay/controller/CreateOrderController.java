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
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateOrderController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserAccount user;
        Guest guest;
        String userType;
        int userID;
        CartItemManager cartItemManager = (CartItemManager) session.getAttribute("cartItemManager");
        OrderLineDBManager orderLineManager = (OrderLineDBManager) session.getAttribute("orderLineManager");
        OrderDBManager orderManager = (OrderDBManager) session.getAttribute("orderManager");
        ArrayList<CartItem> cartItems = (ArrayList<CartItem>) session.getAttribute("cartItems");
        boolean isLoggedIn = (boolean) session.getAttribute("isLoggedIn");
//        Cart cart = (Cart) session.getAttribute("cart");
        if(!isLoggedIn) {
            guest = (Guest) session.getAttribute("guest");
            userType = "guest";
            userID = guest.getID();
        } else {
            user = (UserAccount) session.getAttribute("user");
            userType = "user";
            userID = user.getID();
        }


        try {
            int orderID = orderManager.createOrder(userID, userType);
            if(!cartItems.isEmpty()) {
                double total = 0.0;
                for(CartItem cartItem : cartItems) {
                    orderLineManager.createOrderLineItem(orderID, cartItem);
                    cartItemManager.removeCartItem(cartItem.getID());
                    total += (cartItem.getPrice() * cartItem.getQuantity());
                    total = round(total, 2);
                }
                orderManager.updateTotal(orderID, total);
            }
            request.getRequestDispatcher("MainServlet").include(request, response);
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(CreateOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}

