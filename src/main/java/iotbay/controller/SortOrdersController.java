package iotbay.controller;

import iotbay.model.ShopOrder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortOrdersController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<ShopOrder> orders = (ArrayList<ShopOrder>) session.getAttribute("orders");
        String sortString = request.getParameter("sort");
        switch (sortString) {
            case "SortIDLow":
                Collections.sort(orders, Comparator.comparing(ShopOrder::getID));
                break;
            case "SortIDHigh":
                Collections.sort(orders, Comparator.comparing(ShopOrder::getID, Comparator.reverseOrder()));
                break;
            case "SortPriceLow":
                Collections.sort(orders, Comparator.comparing(ShopOrder::getTotal));
                break;
            case "SortPriceHigh":
                Collections.sort(orders, Comparator.comparing(ShopOrder::getTotal, Comparator.reverseOrder()));
                break;
            case "SortDateNew":
                Collections.sort(orders, Comparator.comparing(ShopOrder::getDate, Comparator.reverseOrder()));
                break;
            case "SortDateOld":
                Collections.sort(orders, Comparator.comparing(ShopOrder::getDate));
                break;
            case "SortStatusLow":
                Collections.sort(orders, Comparator.comparing(ShopOrder::getStatusID));
                break;
            case "SortStatusHigh":
                Collections.sort(orders, Comparator.comparing(ShopOrder::getStatusID, Comparator.reverseOrder()));
                break;
        }
        session.setAttribute("orders", orders);
        request.getRequestDispatcher("orders.jsp").include(request, response);
    }
}
