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

public class AddToExistingOrderController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        CartItemManager cartItemManager = (CartItemManager) session.getAttribute("cartItemManager");
        OrderLineDBManager orderLineManager = (OrderLineDBManager) session.getAttribute("orderLineManager");
        OrderDBManager orderManager = (OrderDBManager) session.getAttribute("orderManager");
        ArrayList<CartItem> cartItems = (ArrayList<CartItem>) session.getAttribute("cartItems");

        String orderID = request.getParameter("hiddenOrderID");

        if(orderID.equals("-") || orderID.equals(""))  {
            request.getRequestDispatcher("main.jsp").include(request, response);
        } else {
            int orderIDParsed = Integer.parseInt(orderID);
            try {
                if (!cartItems.isEmpty()) {
                    double total = 0.0;
                    for (CartItem cartItem : cartItems) {
                        orderLineManager.createOrderLineItem(orderIDParsed, cartItem);
                        cartItemManager.removeCartItem(cartItem.getID());
                        total += (cartItem.getPrice() * cartItem.getQuantity());
                    }
                    double currentTotal = orderManager.getOrderTotal(orderIDParsed);
                    orderManager.updateTotal(orderIDParsed, (currentTotal + total));
                }
                request.getRequestDispatcher("MainServlet").include(request, response);
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(CreateOrderController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
