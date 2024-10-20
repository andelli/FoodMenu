package com.andeli.foodmenu.model;

import java.io.Serializable;

public class Food implements Serializable {
    private String name, description, price;
    private int imageResId;

    public Food(String name, String description, String price, int imageResId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPrice() { return price; }
    public int getImageResId() { return imageResId; }
}
