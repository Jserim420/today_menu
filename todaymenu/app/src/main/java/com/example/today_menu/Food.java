package com.example.today_menu;

public class Food {

    private String id="";
    private String name="";
    private int price=0;
    private String country="";
    private String categoryFood="";
    private String taste="";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategoryFood() {
        return categoryFood;
    }

    public void setCategoryFood(String categoryFood) {
        this.categoryFood = categoryFood;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }
}
