package iotbay.model.dao;

import iotbay.model.ShippingMethod;
import iotbay.model.UserAccount;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShippingMethodDBManagerTest {

    @Test
    void getShippingMethods() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            ShippingMethodDBManager shippingMethodDBManager = new ShippingMethodDBManager(conn);

            ArrayList<ShippingMethod> shippingMethods = shippingMethodDBManager.getShippingMethods();
            assertNotNull(shippingMethods);
            assertEquals(4, shippingMethods.size());

            for(int i = 0; i < shippingMethods.size(); i++) {
                assertEquals(i + 1, shippingMethods.get(i).getID());
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing getShippingMethods: " + e.getMessage());
        }
    }

    @Test
    void getShippingMethod() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            ShippingMethodDBManager shippingMethodDBManager = new ShippingMethodDBManager(conn);

            ArrayList<ShippingMethod> shippingMethods = shippingMethodDBManager.getShippingMethods();
            assertNotNull(shippingMethods);

            for(int i = 0; i < shippingMethods.size(); i++) {
                int id = shippingMethods.get(i).getID();
                String name = shippingMethods.get(i).getName();
                ShippingMethod shippingMethod = shippingMethodDBManager.getShippingMethod(id);
                assertEquals(i + 1, shippingMethod.getID());
                assertEquals(name, shippingMethod.getName());
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing getShippingMethod: " + e.getMessage());
        }
    }
}