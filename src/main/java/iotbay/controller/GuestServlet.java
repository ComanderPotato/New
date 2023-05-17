package iotbay.controller;

import iotbay.model.*;
import iotbay.model.dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuestServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        GuestDBManager guestManager = (GuestDBManager) session.getAttribute("guestManager");
        CartDBManager cartManager = (CartDBManager) session.getAttribute("cartManager");
        session.setAttribute("isLoggedIn", false);
        Guest guest;
        try {
            guest = guestManager.createGuest();
            cartManager.createCart(guest.getID(), "guest");
            Cart cart = cartManager.getCart(guest.getID(), "guest");
            session.setAttribute("cart", cart);
            session.setAttribute("guest", guest);
            request.getRequestDispatcher("MainServlet").include(request, response);
        } catch (NullPointerException | SQLException ex) {
            Logger.getLogger(GuestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        PrintWriter out = resp.getWriter();
//        out.println("<html><head><title>A Simple Servlet</title></head><body>");
//        out.println("Today is "+(new java.util.Date()));
//        out.println("</body></html>");
//        out.close();
//    }
}
