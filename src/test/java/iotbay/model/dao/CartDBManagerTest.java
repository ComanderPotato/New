package iotbay.model.dao;

import iotbay.model.Cart;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CartDBManagerTest {

    @Test
    void getCart() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            CartDBManager cartDBManager = new CartDBManager(conn);


            Cart cart = cartDBManager.getCart(2, "user");
            assertNotNull(cart);
            assertEquals(2, cart.getID());
            assertEquals(2, cart.getUserAccountID());


            cart = cartDBManager.getCart(3, "user");
            assertNotNull(cart);
            assertEquals(3, cart.getID());
            assertEquals(3, cart.getUserAccountID());

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing getCart: " + e.getMessage());
        }
    }
}
