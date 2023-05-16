package iotbay.model;

import java.io.Serializable;

public class Guest implements Serializable {
    private int ID;
    private int cartID;

    public Guest() {
    }

    public Guest(int ID, int cartID) {
        this.ID = ID;
        this.cartID = cartID;
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
}
