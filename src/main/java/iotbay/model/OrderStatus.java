package iotbay.model;

import java.io.Serializable;

public class OrderStatus implements Serializable {
    private int id;
    private String status;

    public OrderStatus() {
    }

    public OrderStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
