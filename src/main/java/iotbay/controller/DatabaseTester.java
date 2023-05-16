package iotbay.controller;

import iotbay.model.Customer;
import iotbay.model.OrderLineItem;
import iotbay.model.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseTester {

    private static Scanner sc = new Scanner(System.in);
    private static CustomerDBManager cdb;
    private static Customer customer;
    public static void main(String[] args) {
        try {
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            // Instantiate what DAO object you want to test

            cdb = new CustomerDBManager(conn);

                    LocalDate date = LocalDate.now();
            Customer c = new Customer("tomgolding2012@outlook.com", "1", "Tom", "Golding", date, "0417503531");
            cdb.addCustomer(c);

//            menu();
            connector.closeConnection();
            // Call function here you want to test


        } catch(ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomerDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
