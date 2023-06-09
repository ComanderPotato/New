package iotbay.controller;

import iotbay.model.Cart;
import iotbay.model.CartItem;
import iotbay.model.UserAccount;
import iotbay.model.dao.CartDBManager;
import iotbay.model.dao.CartItemManager;
import iotbay.model.dao.UserAccountDBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {

        HttpSession session = request.getSession();
        Validator validator = new Validator();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserAccountDBManager account = (UserAccountDBManager) session.getAttribute("account");
        CartDBManager cartManager = (CartDBManager) session.getAttribute("cartManager");


        UserAccount user = null;
        validator.clear(session);
       if(!validator.validateEmail(email)) {
           session.setAttribute("emailErr", "Error: Email format incorrect");
           request.getRequestDispatcher("index.jsp").include(request, response);
       } else if(!validator.validatePassword(password)) {
           session.setAttribute("passErr", "Error: Password format incorrect");
           request.getRequestDispatcher("index.jsp").include(request, response);
       } else {
           try {
               user = account.authenticateUser(email, password);
               if(user != null) {
                   session.setAttribute("isLoggedIn", true);
                   session.setAttribute("user", user);
                   session.setAttribute("cart", cartManager.getCart(user.getID(), "user"));

                   request.getRequestDispatcher("MainServlet").include(request, response);
               } else {
                   session.setAttribute("existErr", "Error: user does not exist");
                   request.getRequestDispatcher("index.jsp").include(request, response);
               }
           } catch (SQLException | NullPointerException ex) {
               System.out.println(ex.getMessage() == null ? "user does not exist" : "Welcome");
           }
       }
    }
}
