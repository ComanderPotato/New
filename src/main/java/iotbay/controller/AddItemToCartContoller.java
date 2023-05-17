package iotbay.controller;

import iotbay.model.Cart;
import iotbay.model.Guest;
import iotbay.model.Product;
import iotbay.model.UserAccount;
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

public class AddItemToCartContoller extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserAccount user;
        Guest guest;
        String userType;
        int userID;
        CartItemManager cartItemManager = (CartItemManager) session.getAttribute("cartItemManager");
        if(session.getAttribute("user") == null) {
            guest = (Guest) session.getAttribute("guest");
            userType = "guest";
            userID = guest.getID();
        } else {
            user = (UserAccount) session.getAttribute("user");
            userType = "user";
            userID = user.getID();
        }
        Product selectedProduct = (Product) session.getAttribute("selectedProduct");
        CartDBManager cartManager = (CartDBManager) session.getAttribute("cartManager");
//        int quantity = (int) session.getAttribute("quantity");
        Cart cart = (Cart) session.getAttribute("cart");
        try {
            cartItemManager.addCartItem(cart.getID(), selectedProduct, 1);
            request.getRequestDispatcher("main.jsp").include(request, response);
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(AddItemToCartContoller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
