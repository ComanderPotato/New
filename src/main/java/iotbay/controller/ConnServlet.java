package iotbay.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import iotbay.model.dao.*;


@WebServlet(name="ConnServlet", value="/ConnServlet")
public class ConnServlet extends HttpServlet {

    private DBConnector db;
    private UserAccountDBManager account;
    private CustomerDBManager customer;
    private ProductDBManager productManager;
    private GuestDBManager guestManager;
    private CartDBManager cartManager;
    CartItemManager cartItemManager;
    private Connection conn;

    @Override //Create and instance of DBConnector for the deployment session
    public void init() {
        try {
            db = new DBConnector();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override //add the DBConnector, DBManager, Connection instances to the session
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        conn = db.openConnection();
        try {
            customer = new CustomerDBManager(conn);
            account = new UserAccountDBManager(conn);
            productManager = new ProductDBManager(conn);
            guestManager = new GuestDBManager(conn);
            cartManager = new CartDBManager(conn);
            cartItemManager = new CartItemManager(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //export the DB manager to the view-session (JSPs)
        session.setAttribute("customer", customer);
        session.setAttribute("account", account);
        session.setAttribute("productManager", productManager);
        session.setAttribute("guestManager", guestManager);
        session.setAttribute("cartManager", cartManager);
        session.setAttribute("cartItemManager", cartItemManager);
    }
    @Override //Destroy the servlet and release the resources of the application (terminate also the db connection)
    public void destroy() {
        try {

            db.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}