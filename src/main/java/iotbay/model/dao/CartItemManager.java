package iotbay.model.dao;

import iotbay.model.Product;

import java.io.Serializable;
import java.sql.*;

public class CartItemManager implements Serializable {
    private Statement stmt;
    private PreparedStatement prepStmt;
    private Connection conn;
    ResultSet rs;

    public CartItemManager(Connection conn) throws SQLException {
        stmt = conn.createStatement();
        this.conn = conn;
    }

    public void addCartItem(int cartID, Product product, int quantity) throws SQLException {
        prepStmt = conn.prepareStatement("INSERT INTO CARTITEM (CARTID, PRODUCTID, PRICE, QUANTITY) VALUES (?, ?, ?, ?)");
        prepStmt.setInt(1, cartID);
        prepStmt.setInt(2, product.getID());
        prepStmt.setDouble(3, product.getPrice());
        prepStmt.setInt(4, quantity);
        prepStmt.executeUpdate();
    }
}
