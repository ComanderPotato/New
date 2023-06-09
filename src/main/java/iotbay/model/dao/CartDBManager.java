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
        if(userType.equals("guest")) {
            sql = "SElECT ID, GUESTID FROM CART WHERE GUESTID = ?";
        } else {
            sql = "SELECT ID, USERACCOUNTID FROM CART WHERE USERACCOUNTID = ?";
        }
        prepStmt = conn.prepareStatement(sql);
        prepStmt.setInt(1, id);

        rs = prepStmt.executeQuery();
        if(rs.next()) {
            return new Cart(rs.getInt(1), rs.getInt(2), userType);
        }
        return null;
    }
}