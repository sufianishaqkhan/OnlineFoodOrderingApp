package com.usamaqadeer.freshveg.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ProductsModel {
    @SerializedName("p_id")
    public int p_id;
    @SerializedName("p_name")
    public String p_name;
    @SerializedName("p_category")
    public String p_category;
    @SerializedName("p_unitprice")
    public double p_unitprice;
    @SerializedName("p_qty")
    public int p_qty;
    @SerializedName("p_weight")
    public String p_weight;
    @SerializedName("p_pic")
    public String p_pic;
    @SerializedName("CreatedDate")
    public Date CreatedDate;

    public ProductsModel(int p_id, String p_name, String p_category, double p_unitprice, int p_qty, String p_weight, String p_pic, Date createdDate) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_category = p_category;
        this.p_unitprice = p_unitprice;
        this.p_qty = p_qty;
        this.p_weight = p_weight;
        this.p_pic = p_pic;
        CreatedDate = createdDate;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_category() {
        return p_category;
    }

    public void setP_category(String p_category) {
        this.p_category = p_category;
    }

    public double getP_unitprice() {
        return p_unitprice;
    }

    public void setP_unitprice(double p_unitprice) {
        this.p_unitprice = p_unitprice;
    }

    public int getP_qty() {
        return p_qty;
    }

    public void setP_qty(int p_qty) {
        this.p_qty = p_qty;
    }

    public String getP_weight() {
        return p_weight;
    }

    public void setP_weight(String p_weight) {
        this.p_weight = p_weight;
    }

    public String getP_pic() {
        return p_pic;
    }

    public void setP_pic(String p_pic) {
        this.p_pic = p_pic;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }
}
