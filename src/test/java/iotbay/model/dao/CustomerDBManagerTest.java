package iotbay.model.dao;

import iotbay.model.Customer;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

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

    @Test
    void addCustomer() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            CustomerDBManager customerDBManager = new CustomerDBManager(conn);

            String email = "jeffy@outlook.com";
            String password = "password10";
            String firstName = "Jeffy";
            String lastName = "Jefferson";
            LocalDate dob = LocalDate.of(1999, 3, 13);
            String phone = "0417503531";

            Customer newCustomer = new Customer(email, password, firstName, lastName, dob, phone);
            customerDBManager.addCustomer(newCustomer);

            Customer retrievedCustomer = customerDBManager.getCustomer(email, password);
            assertEquals(email, retrievedCustomer.getEmail());
            assertEquals(password, retrievedCustomer.getPassword());
            assertEquals(firstName, retrievedCustomer.getFirstName());
            assertEquals(lastName, retrievedCustomer.getLastName());
            assertEquals(dob, retrievedCustomer.getDOB());
            assertEquals(phone, retrievedCustomer.getPhoneNo());

            customerDBManager.deleteCustomerByID(retrievedCustomer.getID());


            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing addCustomer: " + e.getMessage());
        }
    }

    @Test
    void updatedCustomer() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            CustomerDBManager customerDBManager = new CustomerDBManager(conn);
            String email = "bobjefferson@outlook.com";
            String password = "password10";
            String firstName = "Bob";
            String lastName = "Jefferson";
            LocalDate dob = LocalDate.of(1999, 3, 13);
            String phone = "0417503531";
            Customer newDetails =
                    new Customer(email, password, firstName, lastName, dob, phone);
            int userID = 4;

            customerDBManager.updatedCustomer(userID, newDetails);
            Customer updatedCustomer = customerDBManager.getCustomerById(userID);
            assertEquals(updatedCustomer.getEmail(), email);
            assertEquals(updatedCustomer.getPassword(), password);
            assertEquals(updatedCustomer.getFirstName(), firstName);
            assertEquals(updatedCustomer.getLastName(), lastName);
            assertEquals(updatedCustomer.getDOB(), dob);
            assertEquals(updatedCustomer.getPhoneNo(), phone);

            email = "jerryseinfeld@yahoo.com";
            password = "seinfeldIsCool1";
            firstName = "Jerry";
            lastName = "Seinfeld";
            dob = LocalDate.of(1954, 4, 29);
            phone = "0444123499";
            newDetails = new Customer(email, password, firstName, lastName, dob, phone);

            customerDBManager.updatedCustomer(userID, newDetails);

            updatedCustomer = customerDBManager.getCustomerById(userID);
            assertEquals(updatedCustomer.getEmail(), email);
            assertEquals(updatedCustomer.getPassword(), password);
            assertEquals(updatedCustomer.getFirstName(), firstName);
            assertEquals(updatedCustomer.getLastName(), lastName);
            assertEquals(updatedCustomer.getDOB(), dob);
            assertEquals(updatedCustomer.getPhoneNo(), phone);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing updatedCustomer: " + e.getMessage());
        }
    }
    @Test
    void deleteCustomerByID() {
    }
}