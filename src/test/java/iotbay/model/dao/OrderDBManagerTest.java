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
            String userType = "user";
            int id = 3;
            ArrayList<ShopOrder> orders = orderDBManager.fetchOrders(id, userType);

            for(int i = 0; i < orders.size(); i++) {
                ShopOrder currentOrder = orders.get(i);
                ShopOrder order = orderDBManager.getOrder(currentOrder.getID(), userType);
                assertEquals(currentOrder.getID(), order.getID());
                assertEquals(currentOrder.getDate(), order.getDate());
                assertEquals(currentOrder.getStatusID(), order.getStatusID());
                assertEquals(currentOrder.getShippingMethodID(), order.getShippingMethodID());
                assertEquals(currentOrder.getUserAccountID(), order.getUserAccountID());
                assertEquals(currentOrder.getPaymentID(), order.getPaymentID());
            }
            userType = "user";
            id = 6;
            orders = orderDBManager.fetchOrders(id, userType);

            for(int i = 0; i < orders.size(); i++) {
                ShopOrder currentOrder = orders.get(i);
                ShopOrder order = orderDBManager.getOrder(currentOrder.getID(), userType);
                assertEquals(currentOrder.getID(), order.getID());
                assertEquals(currentOrder.getDate(), order.getDate());
                assertEquals(currentOrder.getStatusID(), order.getStatusID());
                assertEquals(currentOrder.getShippingMethodID(), order.getShippingMethodID());
                assertEquals(currentOrder.getUserAccountID(), order.getUserAccountID());
                assertEquals(currentOrder.getPaymentID(), order.getPaymentID());
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing fetchOrders: " + e.getMessage());
        }
    }

    @Test
    void getOrderTotal() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            OrderDBManager orderDBManager = new OrderDBManager(conn);
            int id = 3;
            String userType = "user";
            double total = orderDBManager.getOrderTotal(id);
            ShopOrder order = orderDBManager.getOrder(id, userType);

            assertEquals(order.getTotal(), total);

            id = 4;
            userType = "user";
            total = orderDBManager.getOrderTotal(id);
            order = orderDBManager.getOrder(id, userType);

            assertEquals(order.getTotal(), total);

            id = 5;
            userType = "user";
            total = orderDBManager.getOrderTotal(id);
            order = orderDBManager.getOrder(id, userType);

            assertEquals(order.getTotal(), total);

            id = 50;
            userType = "user";
            total = orderDBManager.getOrderTotal(id);
            order = orderDBManager.getOrder(id, userType);
            assertNull(order);
            assertEquals(0.0, total);

            id = 7;
            userType = "guest";
            total = orderDBManager.getOrderTotal(id);
            order = orderDBManager.getOrder(id, userType);
            assertEquals(order.getTotal(), total);

            id = 8;
            userType = "guest";
            total = orderDBManager.getOrderTotal(id);
            order = orderDBManager.getOrder(id, userType);
            assertEquals(order.getTotal(), total);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing getOrderTotal: " + e.getMessage());
        }
    }

    @Test
    void createOrder() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            OrderDBManager orderDBManager = new OrderDBManager(conn);
            int id = 2;
            String userType = "user";
            int createdOrder = orderDBManager.createOrder(id, userType);
            ShopOrder currentOrder = orderDBManager.getOrder(createdOrder, userType);
            ShopOrder order = orderDBManager.getOrder(currentOrder.getID(), userType);
            assertEquals(currentOrder.getID(), order.getID());
            assertEquals(currentOrder.getDate(), order.getDate());
            assertEquals(currentOrder.getStatusID(), order.getStatusID());
            assertEquals(currentOrder.getShippingMethodID(), order.getShippingMethodID());
            assertEquals(currentOrder.getUserAccountID(), order.getUserAccountID());
            assertEquals(currentOrder.getPaymentID(), order.getPaymentID());

            orderDBManager.deleteOrder(createdOrder);

            id = 2;
            userType = "guest";
            createdOrder = orderDBManager.createOrder(id, userType);
            currentOrder = orderDBManager.getOrder(createdOrder, userType);
            order = orderDBManager.getOrder(currentOrder.getID(), userType);
            assertEquals(currentOrder.getID(), order.getID());
            assertEquals(currentOrder.getDate(), order.getDate());
            assertEquals(currentOrder.getStatusID(), order.getStatusID());
            assertEquals(currentOrder.getShippingMethodID(), order.getShippingMethodID());
            assertEquals(currentOrder.getUserAccountID(), order.getUserAccountID());
            assertEquals(currentOrder.getPaymentID(), order.getPaymentID());

            orderDBManager.deleteOrder(createdOrder);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing createOrder: " + e.getMessage());
        }
    }
    @Test
    void updateTotal() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            OrderDBManager orderDBManager = new OrderDBManager(conn);
            int id = 2;
            String userType = "user";
            int createdOrder = orderDBManager.createOrder(id, userType);
            double total = 50.00;
            orderDBManager.updateTotal(createdOrder, total);
            double orderTotal = orderDBManager.getOrderTotal(createdOrder);
            assertEquals(orderTotal, total);

            total = 41.23;
            orderDBManager.updateTotal(createdOrder, total);
            orderTotal = orderDBManager.getOrderTotal(createdOrder);

            assertEquals(orderTotal, total);


            total = 102.44;
            orderDBManager.updateTotal(createdOrder, total);
            orderTotal = orderDBManager.getOrderTotal(createdOrder);

            assertEquals(orderTotal, total);

            orderDBManager.deleteOrder(createdOrder);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing updateTotal: " + e.getMessage());
        }
    }
    @Test
    void getOrder() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            OrderDBManager orderDBManager = new OrderDBManager(conn);
            int id = 3;
            String userType = "user";
            ShopOrder currentOrder = orderDBManager.getOrder(id, userType);
            double total = orderDBManager.getOrderTotal(id);
            assertEquals(total, currentOrder.getTotal());

            id = 4;
            userType = "user";
            currentOrder = orderDBManager.getOrder(id, userType);
            total = orderDBManager.getOrderTotal(id);
            assertEquals(total, currentOrder.getTotal());

            id = 5;
            userType = "user";
            currentOrder = orderDBManager.getOrder(id, userType);
            total = orderDBManager.getOrderTotal(id);
            assertEquals(total, currentOrder.getTotal());

            id = 7;
            userType = "guest";
            currentOrder = orderDBManager.getOrder(id, userType);
            total = orderDBManager.getOrderTotal(id);
            assertEquals(total, currentOrder.getTotal());

            id = 8;
            userType = "user";
            currentOrder = orderDBManager.getOrder(id, userType);
            total = orderDBManager.getOrderTotal(id);
            assertEquals(total, currentOrder.getTotal());
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing getOrder: " + e.getMessage());
        }
    }

    @Test
    void cancelOrder() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            OrderDBManager orderDBManager = new OrderDBManager(conn);
            int defaultStatusCode = 1;
            int canceledStatusCode = 7;
            int id = 2;
            String userType = "user";
            int createdOrder = orderDBManager.createOrder(id, userType);
            ShopOrder currentOrder = orderDBManager.getOrder(createdOrder, userType);

            assertEquals(defaultStatusCode, currentOrder.getStatusID());

            orderDBManager.cancelOrder(createdOrder);
            ShopOrder canceledOrder = orderDBManager.getOrder(createdOrder, userType);

            assertEquals(canceledStatusCode, canceledOrder.getStatusID());

            orderDBManager.deleteOrder(createdOrder);

            id = 2;
            userType = "guest";
            createdOrder = orderDBManager.createOrder(id, userType);
            currentOrder = orderDBManager.getOrder(createdOrder, userType);

            assertEquals(defaultStatusCode, currentOrder.getStatusID());

            orderDBManager.cancelOrder(createdOrder);

            canceledOrder = orderDBManager.getOrder(createdOrder, userType);

            assertEquals(canceledStatusCode, canceledOrder.getStatusID());

            orderDBManager.deleteOrder(createdOrder);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing cancelOrder: " + e.getMessage());
        }
    }


    @Test
    void updateMethod() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            OrderDBManager orderDBManager = new OrderDBManager(conn);
            int statusIDOne = 1;
            int statusIDTwo = 2;
            int statusIDThree = 3;
            int statusIDFour = 4;

            int id = 2;
            String userType = "user";
            int createdOrderID = orderDBManager.createOrder(id, userType);
            ShopOrder createdOrder = orderDBManager.getOrder(createdOrderID, userType);

            assertEquals(0, createdOrder.getShippingMethodID());

            orderDBManager.updateMethod(createdOrderID, statusIDOne);

            createdOrder = orderDBManager.getOrder(createdOrderID, userType);

            assertEquals(statusIDOne, createdOrder.getShippingMethodID());

            orderDBManager.updateMethod(createdOrderID, statusIDTwo);

            createdOrder = orderDBManager.getOrder(createdOrderID, userType);

            assertEquals(statusIDTwo, createdOrder.getShippingMethodID());

            orderDBManager.updateMethod(createdOrderID, statusIDThree);

            createdOrder = orderDBManager.getOrder(createdOrderID, userType);

            assertEquals(statusIDThree, createdOrder.getShippingMethodID());

            orderDBManager.updateMethod(createdOrderID, statusIDFour);

            createdOrder = orderDBManager.getOrder(createdOrderID, userType);

            assertEquals(statusIDFour, createdOrder.getShippingMethodID());

            orderDBManager.updateMethod(createdOrderID, statusIDTwo);

            createdOrder = orderDBManager.getOrder(createdOrderID, userType);

            assertNotEquals(statusIDFour, createdOrder.getShippingMethodID());

            orderDBManager.deleteOrder(createdOrderID);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing cancelOrder: " + e.getMessage());
        }
    }
}