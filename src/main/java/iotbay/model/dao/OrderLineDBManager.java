package iotbay.model.dao;
import iotbay.model.CartItem;
import iotbay.model.Guest;
import iotbay.model.Product;
import java.sql.*;
import java.util.ArrayList;

public class OrderLineDBManager {
    private Statement stmt;
    private PreparedStatement prepStmt;
    private Connection conn;
    private ResultSet rs;

    public OrderLineDBManager(Connection conn) throws SQLException {
        stmt = conn.createStatement();
        this.conn = conn;
    }

    public void createOrderLineItem(int orderID, CartItem cartItem) throws SQLException {
        prepStmt = conn.prepareStatement("INSERT INTO ORDERLINEITEM (PRODUCTID, ORDERID, QUANTITY, PRICE) VALUES (?, ?, ?, ?)");
        prepStmt.setInt(1, cartItem.getProductID());
        prepStmt.setInt(2, orderID);
        prepStmt.setInt(3, cartItem.getQuantity());
        prepStmt.setDouble(4, cartItem.getPrice());
        prepStmt.executeUpdate();
    }

}