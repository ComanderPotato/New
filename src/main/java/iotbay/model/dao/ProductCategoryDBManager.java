package iotbay.model.dao;
import iotbay.model.CartItem;
import iotbay.model.Guest;
import iotbay.model.Product;
import iotbay.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;

public class ProductCategoryDBManager {
    private Statement stmt;
    private PreparedStatement prepStmt;
    private Connection conn;
    private ResultSet rs;

    public ProductCategoryDBManager(Connection conn) throws SQLException {
        stmt = conn.createStatement();
        this.conn = conn;
    }

    public String getCategory(int id) throws SQLException {
        prepStmt = conn.prepareStatement("SELECT (CATEGORY) FROM PRODUCTCATEGORY WHERE ID = ?");
        prepStmt.setInt(1, id);

        rs = prepStmt.executeQuery();
        if(rs.next()) {
            return rs.getString(1);
        }
        return "";
    }
    public ArrayList<ProductCategory> fetchCategories() throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM PRODUCTCATEGORY");
        rs = prepStmt.executeQuery();
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        while(rs.next()) {
            productCategories.add(new ProductCategory(rs.getInt(1), rs.getString(2)));
        }
        return productCategories;
    }
}