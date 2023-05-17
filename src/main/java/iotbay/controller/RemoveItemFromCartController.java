package iotbay.controller;

import iotbay.model.Product;
import iotbay.model.dao.CartItemManager;
import iotbay.model.dao.ProductDBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoveItemFromCartController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {
    HttpSession session = request.getSession();

        CartItemManager cartItemManager = (CartItemManager) session.getAttribute("cartItemManager");
        ProductDBManager productManager = (ProductDBManager) session.getAttribute("productManager");
        int cartItemID = Integer.parseInt(request.getParameter("cartItemID"));
        int cartItemQuantity = Integer.parseInt(request.getParameter("cartItemQuantity"));
        int productID = Integer.parseInt(request.getParameter("productID"));
        int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));


        try {
            productManager.updateQuantity(productID, (productQuantity + cartItemQuantity));
            cartItemManager.removeCartItem(cartItemID);
            request.getRequestDispatcher("MainServlet").include(request, response);
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(AddItemToCartContoller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
