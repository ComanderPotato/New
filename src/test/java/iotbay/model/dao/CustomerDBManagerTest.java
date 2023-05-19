package iotbay.model.dao;

import iotbay.model.Customer;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDBManagerTest {

    @Test
    void getCustomerById() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            CustomerDBManager customerDBManager = new CustomerDBManager(conn);

            Customer customer = customerDBManager.getCustomerById(2);

            assertNotNull(customer);

            customer = customerDBManager.getCustomerById(4);

            assertNotNull(customer);

            customer = customerDBManager.getCustomerById(10);

            assertNull(customer);

            customer = customerDBManager.getCustomerById(6);

            assertNotNull(customer);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing getCustomerByID: " + e.getMessage());
        }
    }

    @Test
    void customerExists() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            CustomerDBManager customerDBManager = new CustomerDBManager(conn);

            boolean exists = customerDBManager.customerExists("tomgolding2012@outlook.com");

            assertEquals(true, exists);

            exists = customerDBManager.customerExists("tomgolding2008@outlook.com");

            assertEquals(true, exists);

            exists = customerDBManager.customerExists("tomsilvering20012@outlook.com");

            assertEquals(false, exists);

            exists = customerDBManager.customerExists("tomgolding2001@outlook.com");

            assertEquals(true, exists);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing customerExists: " + e.getMessage());
        }
    }


}