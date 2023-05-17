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
import java.util.ArrayList;
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
        CartDBManager cartManager = (CartDBManager) session.getAttribute("cartManager");
        ProductDBManager productManager = (ProductDBManager) session.getAttribute("productManager");

        if(session.getAttribute("user") == null) {
            guest = (Guest) session.getAttribute("guest");
            userType = "guest";
            userID = guest.getID();
        } else {
            user = (UserAccount) session.getAttribute("user");
            userType = "user";
            userID = user.getID();
        }
        int selectedProductID = Integer.parseInt(request.getParameter("selectedProductID"));
        double selectedProductPrice = Double.parseDouble(request.getParameter("selectedProductPrice"));
        String quantity = request.getParameter("quantity");
        int quantityParsed;
        if(quantity == null || quantity.isEmpty()) {
            request.getRequestDispatcher("main.jsp").include(request, response);
        } else {
            quantityParsed = Integer.parseInt(quantity);
            Cart cart = (Cart) session.getAttribute("cart");
            try {
                Product product = productManager.getProduct(selectedProductID);
                if(product.getQuantity() < quantityParsed) {
                    request.getRequestDispatcher("MainServlet").include(request, response);
                } else {
                    productManager.updateQuantity(selectedProductID, (product.getQuantity() - quantityParsed));
                }
                cartItemManager.addCartItem(cart.getID(), selectedProductID, selectedProductPrice, quantityParsed);
                session.setAttribute("cartItems", cartItemManager.fetchCartItems(cart.getID()));
                request.getRequestDispatcher("MainServlet").include(request, response);
            } catch (NullPointerException | SQLException ex) {
                Logger.getLogger(AddItemToCartContoller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
