package iotbay.model;

import java.io.Serializable;

public class OrderLineItem implements Serializable {
    private int ID;
    private int orderID;
    private int productID;
    private int quantity;
    private double price;

    public OrderLineItem() {
    }

    public OrderLineItem(int ID, int orderID, int productID, int quantity, double price) {
        this.ID = ID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
    }

}
