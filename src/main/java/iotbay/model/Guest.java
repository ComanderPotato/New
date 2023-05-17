package iotbay.model;

import java.io.Serializable;

public class Guest implements Serializable {
    private int ID;

    public Guest() {
    }
    public Guest(int ID) {
        this.ID = ID;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
