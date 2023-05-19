package iotbay.model.dao;

import iotbay.model.CartItem;
import iotbay.model.Product;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class CartItemManager implements Serializable {
    private Statement stmt;
    private PreparedStatement prepStmt;
    private Connection conn;
    ResultSet rs;

    public CartItemManager(Connection conn) throws SQLException {
        stmt = conn.createStatement();
        this.conn = conn;
    }

    public void addCartItem(int cartID, int productID, double productPrice, int quantity) throws SQLException {
        prepStmt = conn.prepareStatement("INSERT INTO CARTITEM (CARTID, PRODUCTID, PRICE, QUANTITY) VALUES (?, ?, ?, ?)");
        prepStmt.setInt(1, cartID);
        prepStmt.setInt(2, productID);
        prepStmt.setDouble(3, productPrice);
        prepStmt.setInt(4, quantity);
        prepStmt.executeUpdate();
    }
    public ArrayList<CartItem> fetchCartItems(int id) throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM CARTITEM WHERE CARTID = ?");
        prepStmt.setInt(1, id);
        ArrayList<CartItem> cartItems = new ArrayList<>();
        rs = prepStmt.executeQuery();
        while(rs.next()) {
            cartItems.add(new CartItem(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4), rs.getInt(5)));
        }
        return cartItems;
    }
    public void removeCartItem(int id) throws SQLException {
        prepStmt = conn.prepareStatement("DELETE FROM CARTITEM WHERE ID = ?");
        prepStmt.setInt(1, id);
        prepStmt.executeUpdate();
    }
    public void updateCartItemQuantity(int productID, int cartID, int quantity) throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM CARTITEM WHERE PRODUCTID = ? AND CARTID = ?");
        prepStmt.setInt(1, productID);
        prepStmt.setInt(2, cartID);
        rs = prepStmt.executeQuery();

        if(rs.next()) {
            prepStmt = conn.prepareStatement("UPDATE CARTITEM SET QUANTITY = ? WHERE PRODUCTID = ?");
            int newQuantity = (rs.getInt(5) + quantity);
            prepStmt.setInt(1, newQuantity);
            prepStmt.setInt(2, productID);
            prepStmt.executeUpdate();
        }
    }
    public boolean cartItemExists(int productID, int cartID) throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM CARTITEM WHERE PRODUCTID = ? AND CARTID = ?");
        prepStmt.setInt(1, productID);
        prepStmt.setInt(2, cartID);

        rs = prepStmt.executeQuery();
        if(rs.next()) {
            return true;
        }
        return false;
    }

}
