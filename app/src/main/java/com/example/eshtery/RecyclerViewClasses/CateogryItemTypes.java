package com.example.eshtery.RecyclerViewClasses;

public class CateogryItemTypes {
    private String CategoryName;

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    private int Image;
    public CateogryItemTypes(String CategoryName, int Image)
    {
        this.CategoryName = CategoryName;
        this.Image = Image;
    }
}
