package iotbay.model.dao;
import iotbay.model.ShippingMethod;

import java.sql.*;
import java.util.ArrayList;

public class ShippingMethodDBManager {
    private Statement stmt;
    private PreparedStatement prepStmt;
    private Connection conn;
    private ResultSet rs;

    public ShippingMethodDBManager(Connection conn) throws SQLException {
        stmt = conn.createStatement();
        this.conn = conn;
    }

    public ArrayList<ShippingMethod> getShippingMethods() throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM SHIPPINGMETHOD");
        ArrayList<ShippingMethod> shippingMethods = new ArrayList<>();
        rs = prepStmt.executeQuery();
        while(rs.next()) {
            shippingMethods.add(new ShippingMethod(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
        }
        return shippingMethods;
    }
    public ShippingMethod getShippingMethod(int id) throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM SHIPPINGMETHOD WHERE ID = ?");
        prepStmt.setInt(1, id);
        rs = prepStmt.executeQuery();
        if(rs.next()) {
            return new ShippingMethod(rs.getInt(1), rs.getString(2), rs.getDouble(3));
        }
        return null;
    }
}