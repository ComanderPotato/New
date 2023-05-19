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
                assertEquals(i + 1, products.get(i).getID());
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

}