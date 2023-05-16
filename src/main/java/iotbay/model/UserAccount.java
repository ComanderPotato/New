package iotbay.model;

import java.io.Serializable;

public class UserAccount implements Serializable {
    private int ID;
    private int customerID;
    private String email;
    private String password;

    public UserAccount() {
    }

    public UserAccount(int ID, int customerID, String email, String password) {
        this.ID = ID;
        this.customerID = customerID;
        this.email = email;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
