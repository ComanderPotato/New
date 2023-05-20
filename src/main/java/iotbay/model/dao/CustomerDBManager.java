package iotbay.model.dao;
import iotbay.model.Customer;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerDBManager {
    private Statement stmt;
    private PreparedStatement prepStmt;
    private Connection conn;
    ResultSet rs;

    public CustomerDBManager(Connection conn) throws SQLException {
        stmt = conn.createStatement();
        this.conn = conn;
    }

    public Customer getCustomerById(int id) throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM CUSTOMER WHERE ID = ?");
        prepStmt.setInt(1, id);

        rs = prepStmt.executeQuery();

        if(!rs.next()) {
            System.out.println("No customer for ID: " +id);
            return null;
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse(rs.getString(6), format);
        return new Customer(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                dob,
                rs.getString(7));
    }

    public void addCustomer(Customer customer) throws SQLException {
        prepStmt = conn.prepareStatement("INSERT INTO CUSTOMER " +
                                                 "(EMAIL, PASSWORD, FIRSTNAME, LASTNAME, DOB, PHONENO)" +
                                                 "VALUES (?, ?, ?, ?, ?, ?)");
        prepareCustomer(customer);
        rs = prepStmt.getGeneratedKeys();

        int customerID = rs.getInt(1);
        UserAccountDBManager udb = new UserAccountDBManager(conn);
        udb.addAccount(customerID, customer);
    }

    public void updatedCustomer(int userID, Customer updatedCustomer) throws SQLException {
        prepStmt = conn.prepareStatement("UPDATE CUSTOMER " +
                "SET EMAIL = ?, PASSWORD = ?, FIRSTNAME = ?, LASTNAME = ?, DOB = ?, PHONENO = ?" +
                "WHERE ID = ?");
        prepStmt.setInt(7, userID);
        prepareCustomer(updatedCustomer);
    }
    public boolean customerExists(String email) throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM CUSTOMER WHERE EMAIL = ?");
        prepStmt.setString(1, email);

        rs = prepStmt.executeQuery();

        if(rs.next()) {
            return true;
        }
        return false;
    }
    private void prepareCustomer(Customer customer) throws SQLException {
        prepStmt.setString(1, customer.getEmail());
        prepStmt.setString(2, customer.getPassword());
        prepStmt.setString(3, customer.getFirstName());
        prepStmt.setString(4, customer.getLastName());
        prepStmt.setString(5, customer.getDOBAsString());
        prepStmt.setString(6, customer.getPhoneNo());
        prepStmt.executeUpdate();
    }

    public void deleteCustomerByID(int id) throws SQLException {
        prepStmt = conn.prepareStatement("DELETE FROM CUSTOMER WHERE ID = ?");
        prepStmt.setInt(1, id);
        prepStmt.executeUpdate();
    }
    public Customer getCustomer(String email, String password) throws SQLException {
        prepStmt = conn.prepareStatement("SELECT * FROM CUSTOMER WHERE EMAIL = ? AND PASSWORD = ?");
        prepStmt.setString(1, email);
        prepStmt.setString(2, password);

        rs = prepStmt.executeQuery();
        if(rs.next()) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dob = LocalDate.parse(rs.getString(6), format);
            return new Customer(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    dob,
                    rs.getString(7));
        }
        return null;
    }
}
