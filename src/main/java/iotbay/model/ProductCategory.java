package iotbay.model;

import java.io.Serializable;

public class ProductCategory implements Serializable {
    private int id;
    private String category;

    public ProductCategory() {
    }

    public ProductCategory(int id, String category) {
        this.id = id;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
