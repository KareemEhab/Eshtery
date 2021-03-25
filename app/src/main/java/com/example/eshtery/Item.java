package com.example.eshtery;

public class Item {
    private String Name;
    private String Price;
    private int Image;
    public Item(String Name, String Price, int Image)
    {
        this.Name = Name;
        this. Price = Price;
        this.Image = Image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
