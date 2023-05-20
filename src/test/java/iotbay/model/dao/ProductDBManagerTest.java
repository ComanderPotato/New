package iotbay.model.dao;

import iotbay.model.Product;
import iotbay.model.ShippingMethod;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductDBManagerTest {

    @Test
    void fetchProducts() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            ProductDBManager productDBManager = new ProductDBManager(conn);

            ArrayList<Product> products = productDBManager.fetchProducts();
            assertNotNull(products);

            assertEquals(100, products.size());

            for(int i = 0; i < products.size(); i++) {
                Product currProduct = products.get(i);
                Product retrievedProduct = productDBManager.getProduct(i + 1);
                assertEquals(retrievedProduct.getID(), currProduct.getID());
                assertEquals(retrievedProduct.getImage(), currProduct.getImage());
                assertEquals(retrievedProduct.getQuantity(), currProduct.getQuantity());
                assertEquals(retrievedProduct.getDescription(), currProduct.getDescription());
                assertEquals(retrievedProduct.getCategory(), currProduct.getCategory());

            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing fetchProducts: " + e.getMessage());
        }
    }

    @Test
    void getProduct() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            ProductDBManager productDBManager = new ProductDBManager(conn);

            ArrayList<Product> products = productDBManager.fetchProducts();
            assertNotNull(products);

            assertEquals(100, products.size());

            for(int i = 0; i < products.size(); i++) {
                Product currProduct = products.get(i);
                int id = currProduct.getID();
                int category = currProduct.getCategoryID();
                String name = currProduct.getName();
                String description = currProduct.getDescription();
                String image = currProduct.getImage();
                double price = currProduct.getPrice();
                int quantity = currProduct.getQuantity();
                Product product = productDBManager.getProduct(id);
                assertEquals(i + 1, product.getID());
                assertEquals(category, product.getCategoryID());
                assertEquals(name, product.getName());
                assertEquals(description, product.getDescription());
                assertEquals(image, product.getImage());
                assertEquals(price, product.getPrice());
                assertEquals(quantity, product.getQuantity());
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing getProduct: " + e.getMessage());
        }
    }

    @Test
    void updateQuantity() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            ProductDBManager productDBManager = new ProductDBManager(conn);
            int id = 2;
            Product product = productDBManager.getProduct(id);
            int initialyQuantity = product.getQuantity();

            int newQuantity = 50;
            productDBManager.updateQuantity(id, newQuantity);

            product = productDBManager.getProduct(id);
            int updatedQuantity = product.getQuantity();
            assertEquals(newQuantity, updatedQuantity);

            newQuantity = 24;
            productDBManager.updateQuantity(id, newQuantity);

            product = productDBManager.getProduct(id);
            updatedQuantity = product.getQuantity();
            assertEquals(newQuantity, updatedQuantity);

            newQuantity = 120;
            productDBManager.updateQuantity(id, newQuantity);

            product = productDBManager.getProduct(id);
            updatedQuantity = product.getQuantity();
            assertEquals(newQuantity, updatedQuantity);

            newQuantity = 4;
            productDBManager.updateQuantity(id, newQuantity);

            product = productDBManager.getProduct(id);
            updatedQuantity = product.getQuantity();
            assertEquals(newQuantity, updatedQuantity);

            newQuantity = 99;
            productDBManager.updateQuantity(id, newQuantity);

            product = productDBManager.getProduct(id);
            updatedQuantity = product.getQuantity();
            assertEquals(newQuantity, updatedQuantity);

            newQuantity = initialyQuantity;
            productDBManager.updateQuantity(id, newQuantity);

            product = productDBManager.getProduct(id);
            updatedQuantity = product.getQuantity();
            assertEquals(newQuantity, updatedQuantity);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing getProduct: " + e.getMessage());
        }
    }
}