package iotbay.model.dao;
import iotbay.model.Guest;
import iotbay.model.Product;
import java.sql.*;
import java.util.ArrayList;

public class GuestDBManager {
    private Statement stmt;
    private PreparedStatement prepStmt;
    private Connection conn;
    private ResultSet rs;

    public GuestDBManager(Connection conn) throws SQLException {
        stmt = conn.createStatement();
        this.conn = conn;
    }

    public Guest createGuest() throws SQLException {
        prepStmt = conn.prepareStatement("INSERT INTO GUEST DEFAULT VALUES;");
        prepStmt.executeUpdate();
        rs = prepStmt.getGeneratedKeys();
        if(rs.next()) {
            return new Guest(rs.getInt(1));
        }
        return null;
    }

}