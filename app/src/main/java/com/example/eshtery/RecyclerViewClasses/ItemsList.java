package com.example.eshtery.RecyclerViewClasses;

import android.graphics.Bitmap;

public class ItemsList {
    private Bitmap image;

    public int getSubImage() {
        return subImage;
    }

    public void setSubImage(int subImage) {
        this.subImage = subImage;
    }

    public int getAddImage() {
        return addImage;
    }

    public void setAddImage(int addImage) {
        this.addImage = addImage;
    }

    private int subImage = -9999999;
    private int addImage = -9999999;
    private String name;
    private String price;
    public int quantity = 0;

    public ItemsList(Bitmap image, String name, String price, int subImage, int addImage) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.subImage = subImage;
        this.addImage = addImage;
    }

    public ItemsList(Bitmap image, String name, String price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
