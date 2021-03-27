package com.example.eshtery.RecyclerViewClasses;

public class Items {
    private String Name;
    private String Price;
    private int Image;
    public Items(String Name, String Price, int Image)
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
