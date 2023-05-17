package iotbay.controller;

import iotbay.model.Customer;
import iotbay.model.Product;
import iotbay.model.UserAccount;
import iotbay.model.dao.CustomerDBManager;
import iotbay.model.dao.ProductDBManager;
import iotbay.model.dao.UserAccountDBManager;
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
        validator.clear(session);
        ArrayList<Product> products;
        try {
            products = productManager.fetchProducts();
            session.setAttribute("products", products);
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
        validator.clear(session);
        ArrayList<Product> products;
        try {
            products = productManager.fetchProducts();
            session.setAttribute("products", products);
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);

        }
        request.getRequestDispatcher("main.jsp").include(request, response);
    }
}
