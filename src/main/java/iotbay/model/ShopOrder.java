package iotbay.model;

import java.time.LocalDateTime;

public class ShopOrder {
    private int ID;
    private int userAccountID;
    private int guestID;
    private LocalDateTime date;
    private int paymentID;
    private int addressID;
    private int shippingMethodID;
    private double total;
    private int statusID;

    public ShopOrder() {
    }

    public ShopOrder(int ID,
                     int userID,
                     LocalDateTime date,
                     int paymentID,
                     int addressID,
                     int shippingMethodID,
                     double total,
                     int statusID,
                     String userType) {
        this.ID = ID;
        if(userType.equals("guest")) {
            this.guestID = userID;
        } else {
            this.userAccountID = userID;
        }
        this.date = date;
        this.paymentID = paymentID;
        this.addressID = addressID;
        this.shippingMethodID = shippingMethodID;
        this.total = total;
        this.statusID = statusID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserAccountID() {
        return userAccountID;
    }

    public void setUserAccountID(int userAccountID) {
        this.userAccountID = userAccountID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getShippingMethodID() {
        return shippingMethodID;
    }

    public void setShippingMethodID(int shippingMethodID) {
        this.shippingMethodID = shippingMethodID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }
    public String getDateAsString() {
        String date = this.date.toString();
        date = date.replace('T', ' ');
        return date;
    }
}
