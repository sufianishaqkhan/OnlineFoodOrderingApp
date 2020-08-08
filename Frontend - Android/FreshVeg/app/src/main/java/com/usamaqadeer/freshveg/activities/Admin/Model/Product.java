package com.usamaqadeer.freshveg.activities.Admin.Model;

public class Product {
    int id;
    int Qty;
    double price;
    String name;
    String img;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int quantity) {
        this.Qty = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
