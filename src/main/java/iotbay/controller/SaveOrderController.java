package iotbay.controller;

import iotbay.model.Guest;
import iotbay.model.ShippingMethod;
import iotbay.model.ShopOrder;
import iotbay.model.UserAccount;
import iotbay.model.dao.OrderDBManager;
import iotbay.model.dao.ShippingMethodDBManager;
import jakarta.persistence.criteria.Order;
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

public class SaveOrderController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShippingMethodDBManager shippingMethodManager = (ShippingMethodDBManager) session.getAttribute("shippingMethodManager");
        String methodID = request.getParameter("shippingMethodID");
        String orderID = request.getParameter("orderID");

        if(methodID.equals("")) {
            request.getRequestDispatcher("orders.jsp").include(request, response);
        } else {
            int methodIDParsed = Integer.parseInt(methodID);
            int orderIDParsed = Integer.parseInt(orderID);
            OrderDBManager orderManager = (OrderDBManager) session.getAttribute("orderManager");
            try {
                ShippingMethod shippingMethod = shippingMethodManager.getShippingMethod(methodIDParsed);
                double total = orderManager.getOrderTotal(orderIDParsed);
                orderManager.updateTotal(orderIDParsed, (total + shippingMethod.getPrice()));
                orderManager.updateMethod(orderIDParsed, methodIDParsed);
                request.getRequestDispatcher("OrdersServlet").include(request, response);
            } catch (NullPointerException | SQLException ex) {
                Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
