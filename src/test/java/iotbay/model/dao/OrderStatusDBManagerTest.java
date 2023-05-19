package iotbay.model.dao;

import iotbay.model.ProductCategory;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderStatusDBManagerTest {

    @Test
    void getStatus() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            OrderStatusDBManager orderStatusDBManager = new OrderStatusDBManager(conn);

            String status = orderStatusDBManager.getStatus(1);

            assertEquals("New", status);

            status = orderStatusDBManager.getStatus(4);

            assertEquals("In Progress", status);

            status = orderStatusDBManager.getStatus(3);

            assertEquals("Payment Failed", status);

            status = orderStatusDBManager.getStatus(10);

            assertEquals("", status);

            status = orderStatusDBManager.getStatus(7);

            assertEquals("Cancelled", status);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing getStatus: " + e.getMessage());
        }
    }
}