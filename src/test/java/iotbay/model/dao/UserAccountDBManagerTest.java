package iotbay.model.dao;

import iotbay.model.Cart;
import iotbay.model.UserAccount;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountDBManagerTest {

    @Test
    void authenticateUser() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            UserAccountDBManager userAccountDBManager = new UserAccountDBManager(conn);

            UserAccount userAccount = userAccountDBManager.authenticateUser("tomgolding2012@outlook.com", "password1");

            assertNotNull(userAccount);
            assertEquals(2, userAccount.getID());
            assertEquals(2, userAccount.getCustomerID());

            userAccount = userAccountDBManager.authenticateUser("tomgolding2022@outlook.com", "password1");

            assertNotNull(userAccount);
            assertEquals(4, userAccount.getID());
            assertEquals(4, userAccount.getCustomerID());


            userAccount = userAccountDBManager.authenticateUser("jeremy@outlook.com", "password10");

            assertNull(userAccount);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing authenticateUser: " + e.getMessage());
        }
    }


    @Test
    void findAccount() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            UserAccountDBManager userAccountDBManager = new UserAccountDBManager(conn);

            UserAccount userAccount = userAccountDBManager.findAccount("tomgolding2012@outlook.com", "password1");

            assertNotNull(userAccount);
            assertEquals(2, userAccount.getID());
            assertEquals(2, userAccount.getCustomerID());

            userAccount = userAccountDBManager.findAccount("tomgolding2022@outlook.com", "password1");

            assertNotNull(userAccount);
            assertEquals(4, userAccount.getID());
            assertEquals(4, userAccount.getCustomerID());

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing findAccount: " + e.getMessage());
        }
    }

    @Test
    void findAccountEmail() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            UserAccountDBManager userAccountDBManager = new UserAccountDBManager(conn);

            UserAccount userAccount = userAccountDBManager.findAccount("tomgolding2012@outlook.com");

            assertNotNull(userAccount);
            assertEquals(2, userAccount.getID());
            assertEquals(2, userAccount.getCustomerID());

            userAccount = userAccountDBManager.findAccount("tomgolding2022@outlook.com");

            assertNotNull(userAccount);
            assertEquals(4, userAccount.getID());
            assertEquals(4, userAccount.getCustomerID());

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing findAccountEmail: " + e.getMessage());
        }
    }

    @Test
    void addAccount() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            UserAccountDBManager userAccountDBManager = new UserAccountDBManager(conn);


            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing findAccountEmail: " + e.getMessage());
        }
    }
}