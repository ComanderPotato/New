package iotbay.model.dao;

import iotbay.model.ProductCategory;
import iotbay.model.ShopOrder;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderDBManagerTest {



    @Test
    void fetchOrders() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            OrderDBManager orderDBManager = new OrderDBManager(conn);

            ArrayList<ShopOrder> orders = orderDBManager.fetchOrders(2, "user");

            for(int i = 0; i < orders.size(); i++) {
                ShopOrder currOrder = orders.get(1);
                double fetchedOrder = orderDBManager.getOrderTotal(currOrder.getID());
                assertEquals(fetchedOrder, currOrder.getTotal());

            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing getOrderTotal: " + e.getMessage());
        }
    }

    @Test
    void getOrderTotal() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            OrderDBManager orderDBManager = new OrderDBManager(conn);

            double total = orderDBManager.getOrderTotal(2);

            assertEquals(415.2, total);

            total = orderDBManager.getOrderTotal(3);

            assertEquals(492.65, total);

            total = orderDBManager.getOrderTotal(4);

            assertEquals(517.6, total);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing getOrderTotal: " + e.getMessage());
        }
    }

}