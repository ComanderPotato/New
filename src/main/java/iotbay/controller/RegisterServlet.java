package iotbay.controller;

import iotbay.model.Cart;
import iotbay.model.Customer;
import iotbay.model.UserAccount;
import iotbay.model.dao.CartDBManager;
import iotbay.model.dao.CustomerDBManager;
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
//@WebServlet(name="RegisterServlet", value="/RegisterServlet")

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dob = request.getParameter("dob");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate formatDob = LocalDate.parse(dob, format);
        String phone = request.getParameter("phone");
        CustomerDBManager customer = (CustomerDBManager) session.getAttribute("customer");
        UserAccountDBManager account = (UserAccountDBManager) session.getAttribute("account");
        CartDBManager cartManager = (CartDBManager) session.getAttribute("cartManager");
        Customer newCustomer = null;
        validator.clear(session);
        if(!validator.validateEmail(email)) {
            session.setAttribute("emailErr", "Error: Email format incorrect");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if(!validator.validatePassword(password)) {
            session.setAttribute("passErr", "Error: Password format incorrect");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if(!validator.validatePhone(phone)) {
            session.setAttribute("phoneErr", "Error: Phone number format incorrect");
            request.getRequestDispatcher("register.jsp").include(request, response);
        }
        else {
            try {
                if(account.findAccount(email) == null) {

                    newCustomer = new Customer(email, password, firstName, lastName, formatDob, phone);
                    customer.addCustomer(newCustomer);
                    account.findAccount(email, password);
                    UserAccount user = account.findAccount(email, password);
                    cartManager.createCart(user.getID(), "user");
                    Cart cart = cartManager.getCart(user.getID(), "user");
                    session.setAttribute("cart", cart);
                    session.setAttribute("isLoggedIn", true);
                    if(user != null) {
                        session.setAttribute("user", user);
                        request.getRequestDispatcher("MainServlet").include(request, response);
                    }
                } else {
                    session.setAttribute("userExists", "User with email already exists");
                    request.getRequestDispatcher("register.jsp").include(request, response);
                }
            } catch (SQLException | NullPointerException ex) {
                System.out.println(ex.getMessage() == null ? "Customer does not exist" : "Welcome");
            }
        }

    }
}
