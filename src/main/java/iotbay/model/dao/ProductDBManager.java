package iotbay.model.dao;
import iotbay.model.Product;
import java.sql.*;
import java.util.ArrayList;

public class ProductDBManager {
    private Statement stmt;
    private PreparedStatement prepStmt;
    private Connection conn;
    private ResultSet rs;

    public ProductDBManager(Connection conn) throws SQLException {
        stmt = conn.createStatement();
        this.conn = conn;
    }

    public ArrayList<Product> fetchProducts() throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM PRODUCT");
        rs = prepStmt.executeQuery();
        ArrayList<Product> products = new ArrayList<>();
        while(rs.next()) {
            products.add(new Product(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getDouble(6),
                    rs.getInt(7)));
        }
        return products;
    }
    public Product getProduct(int id) throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM PRODUCT WHERE ID = ?");
        prepStmt.setInt(1, id);
        rs = prepStmt.executeQuery();
        if(rs.next()) {
            return new Product(rs.getInt(1),
                               rs.getInt(2),
                               rs.getString(3),
                               rs.getString(4),
                               rs.getString(5),
                               rs.getDouble(6),
                               rs.getInt(7));
        }
        return null;
    }
    public void updateQuantity(int id, int quantity) throws SQLException {
        prepStmt = conn.prepareStatement("UPDATE PRODUCT SET QUANTITY = ? WHERE ID = ?");
        prepStmt.setInt(1, quantity);
        prepStmt.setInt(2, id);
        prepStmt.executeUpdate();
    }
}