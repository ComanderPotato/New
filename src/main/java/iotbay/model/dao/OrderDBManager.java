package iotbay.model.dao;
import iotbay.model.CartItem;
import iotbay.model.Guest;
import iotbay.model.Product;
import iotbay.model.ShopOrder;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class OrderDBManager {
    private Statement stmt;
    private PreparedStatement prepStmt;
    private Connection conn;
    private ResultSet rs;

    public OrderDBManager(Connection conn) throws SQLException {
        stmt = conn.createStatement();
        this.conn = conn;
    }

    public int createOrder(int userID, String userType) throws SQLException {
        String sql;
        if(userType == "guest") {
            sql = "INSERT INTO SHOPORDER (GUESTID, DATE, TOTAL, STATUSID) VALUES (?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO SHOPORDER (USERACCOUNTID, DATE, TOTAL, STATUSID) VALUES (?, ?, ?, ?)";
        }
        prepStmt = conn.prepareStatement(sql);
        prepStmt.setInt(1, userID);
        prepStmt.setString(2, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString());
        prepStmt.setDouble(3, 0.0);
        prepStmt.setInt(4, 1);
        prepStmt.executeUpdate();
        rs = prepStmt.getGeneratedKeys();

        return rs.getInt(1);
    }
    public void updateTotal(int orderID, double total) throws SQLException {
        prepStmt = conn.prepareStatement("UPDATE SHOPORDER SET TOTAL = ? WHERE ID = ?");
        prepStmt.setDouble(1, total);
        prepStmt.setInt(2, orderID);
        prepStmt.executeUpdate();
    }

    public ArrayList<ShopOrder> fetchOrders(int userID, String userType) throws SQLException {
        String sql;
        if(userType == "guest") {
            sql = "SELECT * FROM SHOPORDER WHERE GUESTID = ?";
        } else {
            sql = "SELECT * FROM SHOPORDER WHERE USERACCOUNTID = ?";
        }
        prepStmt = conn.prepareStatement(sql);
        prepStmt.setInt(1, userID);

        rs = prepStmt.executeQuery();
        ArrayList<ShopOrder> orders = new ArrayList<>();
        while(rs.next()) {
            int ID = rs.getInt("ID");
            String date = rs.getString("date");
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd['T']HH:mm:ss");
            LocalDateTime time = LocalDateTime.parse(date, format);
            int paymentID = rs.getInt("paymentID");
            int addressID = rs.getInt("addressID");
            int shippingMethodID = rs.getInt("shippingMethodID");
            double total = rs.getDouble("total");
            int statusID = rs.getInt("statusID");
            orders.add(new ShopOrder(ID, userID, time, paymentID, addressID, shippingMethodID, total, statusID, userType));
        }
        return orders;
    }
    public ShopOrder getOrder(int orderID, String userType) throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM SHOPORDER WHERE ID = ?");
        prepStmt.setInt(1, orderID);
        rs = prepStmt.executeQuery();
        if(rs.next()) {
            int ID = rs.getInt("ID");
            int userID;
            if(userType.equals("guest")) {
                userID = rs.getInt("guestID");
            } else {
                userID = rs.getInt("userAccountID");
            }
            String date = rs.getString("date");
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd['T']HH:mm:ss");
            LocalDateTime time = LocalDateTime.parse(date, format);
            int paymentID = rs.getInt("paymentID");
            int addressID = rs.getInt("addressID");
            int shippingMethodID = rs.getInt("shippingMethodID");
            double total = rs.getDouble("total");
            int statusID = rs.getInt("statusID");
            return new ShopOrder(ID, userID, time, paymentID, addressID, shippingMethodID, total, statusID, userType);
        }
        return null;
    }
    public void cancelOrder(int id) throws SQLException {
        prepStmt = conn.prepareStatement("UPDATE SHOPORDER SET STATUSID = 7 WHERE ID = ?");
        prepStmt.setInt(1, id);
        prepStmt.executeUpdate();
    }
    public double getOrderTotal(int id) throws SQLException {
        prepStmt = conn.prepareStatement("SElECT (TOTAL) FROM SHOPORDER WHERE ID = ?");
        prepStmt.setInt(1, id);
        rs = prepStmt.executeQuery();

        if(rs.next()) {
            return rs.getDouble(1);
        }
        return 0.0;
    }
    public void updateMethod(int id, int methodID) throws SQLException {
        prepStmt = conn.prepareStatement("UPDATE SHOPORDER SET SHIPPINGMETHODID = ? WHERE ID = ?");
        prepStmt.setInt(1, methodID);
        prepStmt.setInt(2, id);
        prepStmt.executeUpdate();
    }
    public void deleteOrder(int id) throws SQLException {
        prepStmt = conn.prepareStatement("DELETE FROM SHOPORDER WHERE ID = ?");
        prepStmt.setInt(1, id);
        prepStmt.executeUpdate();
    }
}