package iotbay.model.dao;

import iotbay.model.Product;
import iotbay.model.ProductCategory;
import iotbay.model.ShippingMethod;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDBManagerTest {

    @Test
    void getCategory() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            ProductCategoryDBManager productCategoryDBManager = new ProductCategoryDBManager(conn);

            String category = productCategoryDBManager.getCategory(1);

            assertNotNull(category);
            assertEquals("Smart TV", category);

            category = productCategoryDBManager.getCategory(2);

            assertNotNull(category);
            assertEquals("Smart Speaker", category);


            category = productCategoryDBManager.getCategory(5);

            assertNotNull(category);
            assertEquals("Smart Refrigerator", category);

            category = productCategoryDBManager.getCategory(10);

            assertEquals("", category);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing getCategory: " + e.getMessage());
        }
    }

    @Test
    void fetchCategories() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/tomgolding/Desktop/New/IoTBay.db");
            ProductCategoryDBManager productCategoryDBManager = new ProductCategoryDBManager(conn);

            ArrayList<ProductCategory> categories = productCategoryDBManager.fetchCategories();

            assertNotNull(categories);

            assertEquals(7, categories.size());

            for(int i = 0; i < categories.size(); i++) {
                ProductCategory category = categories.get(i);
                assertEquals(i + 1, category.getId());
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while testing fetchCategory: " + e.getMessage());
        }
    }
}