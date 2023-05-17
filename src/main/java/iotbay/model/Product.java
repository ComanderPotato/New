package iotbay.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int ID;
    private int categoryID;
    private String name;
    private String description;
    private String image;
    private double price;
    private int quantity;

    private String category;
    public Product() {
    }

    public Product(int ID,
                   int categoryID,
                   String name,
                   String description,
                   String image,
                   double price,
                   int quantity) {
        this.ID = ID;
        this.categoryID = categoryID;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }
    public Product(int ID, String category, String name, String description, String image, double price, int quantity) {
        this.ID = ID;
        this.category = category;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
