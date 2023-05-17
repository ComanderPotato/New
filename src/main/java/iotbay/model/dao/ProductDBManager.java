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
//            String category = fetchProductCategory(rs.getInt(2));
            products.add(new Product(
                    rs.getInt(1),
                    "category",
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getDouble(6),
                    rs.getInt(7)));
        }
        return products;
    }
    public String fetchProductCategory(int categoryID) throws SQLException {
        System.out.println("Hello");
        prepStmt = conn.prepareStatement("SELECT * FROM PRODUCTCATEGORY WHERE ID = ?");
        prepStmt.setInt(1, categoryID);
        rs = prepStmt.executeQuery();
        if(rs.next()) {
            return rs.getString("category");
        }
        return null;
    }
}