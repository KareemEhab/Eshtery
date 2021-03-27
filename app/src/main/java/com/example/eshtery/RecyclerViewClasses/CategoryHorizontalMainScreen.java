package com.example.eshtery.RecyclerViewClasses;

public class CategoryHorizontalMainScreen {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private String name;
    private int image;
    public CategoryHorizontalMainScreen(String name, int image)
    {
        this.name = name;
        this.image = image;
    }
}
