package iotbay.model;

import java.io.Serializable;

public class CartItem implements Serializable {
    private int ID;
    private int cartID;
    private int productID;
    private double price;
    private int quantity;

    public CartItem() {
    }

    public CartItem(int ID, int cartID, int productID, double price, int quantity) {
        this.ID = ID;
        this.cartID = cartID;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
