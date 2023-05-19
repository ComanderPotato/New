package iotbay.controller;

import iotbay.model.Customer;
import iotbay.model.OrderLineItem;
import iotbay.model.ShippingMethod;
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
    private static Customer customer;
    public static void main(String[] args) {
        try {
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            // Instantiate what DAO object you want to test

           ShippingMethodDBManager s = new ShippingMethodDBManager(conn);

           ArrayList<ShippingMethod> sa = s.getShippingMethods();

            for(ShippingMethod a : sa) {
                System.out.println(a.getName());
            }
            connector.closeConnection();
            // Call function here you want to test


        } catch(ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomerDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
