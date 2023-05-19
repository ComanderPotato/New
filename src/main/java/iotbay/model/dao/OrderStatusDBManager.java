package iotbay.model.dao;
import iotbay.model.CartItem;
import iotbay.model.Guest;
import iotbay.model.Product;
import java.sql.*;
import java.util.ArrayList;

public class OrderStatusDBManager {
    private Statement stmt;
    private PreparedStatement prepStmt;
    private Connection conn;
    private ResultSet rs;

    public OrderStatusDBManager(Connection conn) throws SQLException {
        stmt = conn.createStatement();
        this.conn = conn;
    }

   public String getStatus(int id) throws SQLException {
        prepStmt = conn.prepareStatement("SELECT (STATUS) FROM ORDERSTATUS WHERE ID = ?");
        prepStmt.setInt(1, id);
        rs = prepStmt.executeQuery();
        if(rs.next()) {
            return rs.getString(1);
        }
        return "";
   }

}