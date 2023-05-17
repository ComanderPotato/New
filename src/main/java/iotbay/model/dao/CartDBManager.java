package iotbay.model.dao;
import iotbay.model.Cart;
import iotbay.model.CartItem;
import iotbay.model.Guest;
import iotbay.model.Product;
import java.sql.*;
import java.util.ArrayList;

public class CartDBManager {
    private Statement stmt;
    private PreparedStatement prepStmt;
    private Connection conn;
    private ResultSet rs;

    public CartDBManager(Connection conn) throws SQLException {
        stmt = conn.createStatement();
        this.conn = conn;
    }

    public void createCart(int userID, String userType) throws SQLException {
        String sql;
        if(userType == "guest") {
            sql = "INSERT INTO CART (GUESTID) VALUES (?)";
        } else {
            sql = "INSERT INTO CART (USERACCOUNTID) VALUES (?)";
        }
        prepStmt = conn.prepareStatement(sql);
        prepStmt.setInt(1, userID);
        prepStmt.executeUpdate();
    }

    public Cart getCart(int id, String userType) throws SQLException {
        String sql;
        if(userType == "guest") {
            sql = "SElECT * FROM CART WHERE GUESTID = ?";
        } else {
            sql = "SELECT * FROM CART WHERE USERACCOUNTID = ?";
        }
        prepStmt = conn.prepareStatement(sql);
        prepStmt.setInt(1, id);

        rs = prepStmt.getGeneratedKeys();
        if(rs.next()) {
            if(userType == "guest") {
                return new Cart(rs.getInt(1), rs.getInt(3), userType);
            } else {
                return new Cart(rs.getInt(1), rs.getInt(2), userType);
            }
        }
        return null;
    }
//    public ArrayList<CartItem> fetchCart(int id) throws SQLException {
//
//    }
}