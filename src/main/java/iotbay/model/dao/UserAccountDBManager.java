package iotbay.model.dao;
import iotbay.model.Customer;
import iotbay.model.UserAccount;

import java.sql.*;


public class UserAccountDBManager {
    private Statement stmt;
    private PreparedStatement prepStmt;
    private Connection conn;
    ResultSet rs;

    public UserAccountDBManager(Connection conn) throws SQLException {
        stmt = conn.createStatement();
        this.conn = conn;
    }

    public UserAccount authenticateUser(String email, String password) throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM USERACCOUNT WHERE EMAIL = ? and PASSWORD = ?");
        prepStmt.setString(1, email);
        prepStmt.setString(2, password);

        rs = prepStmt.executeQuery();
        if(!rs.next()) {
            System.out.println("No user account for Email: " + email + ", and password: " + password);
            return null;
        }
        return new UserAccount(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
    }

    public void addAccount(int customerID, Customer customer) throws SQLException {
        prepStmt = conn.prepareStatement("INSERT INTO USERACCOUNT (CUSTOMERID, EMAIL, PASSWORD)" +
                                                 "VALUES (?, ?, ?)");
        prepStmt.setInt(1, customerID);
        prepStmt.setString(2, customer.getEmail());
        prepStmt.setString(3, customer.getPassword());
        prepStmt.executeUpdate();
    }

    public UserAccount findAccount(String email, String password) throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM USERACCOUNT WHERE EMAIL = ? and PASSWORD = ?");
        prepStmt.setString(1, email);
        prepStmt.setString(2, password);

        rs = prepStmt.executeQuery();

        if(rs.next()) {
            return new UserAccount(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
        }
        return null;
    }
    public UserAccount findAccount(String email) throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM USERACCOUNT WHERE EMAIL = ?");
        prepStmt.setString(1, email);

        rs = prepStmt.executeQuery();

        if(rs.next()) {
            return new UserAccount(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
        }
        return null;
    }
    public void deleteAccount(int id) throws SQLException {
        prepStmt = conn.prepareStatement("DELETE FROM USERACCOUNT WHERE ID = ?");
        prepStmt.setInt(1, id);
        prepStmt.executeUpdate();
    }
}
