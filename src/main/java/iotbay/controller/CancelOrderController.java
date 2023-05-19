package iotbay.controller;

import iotbay.model.dao.OrderDBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CancelOrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        OrderDBManager orderManager = (OrderDBManager) session.getAttribute("orderManager");
        int orderID = (int) Integer.parseInt(request.getParameter("orderID"));
        try {
            orderManager.cancelOrder(orderID);
            request.getRequestDispatcher("OrdersServlet").include(request, response);
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(CancelOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
