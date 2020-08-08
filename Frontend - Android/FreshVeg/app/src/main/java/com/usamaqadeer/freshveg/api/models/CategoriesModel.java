package com.usamaqadeer.freshveg.api.models;

import com.google.gson.annotations.SerializedName;

public class CategoriesModel {
    @SerializedName("c_id")
    public int c_id;
    @SerializedName("c_categorytype")
    public String c_categorytype;

    public CategoriesModel(int c_id, String c_categorytype) {
        this.c_id = c_id;
        this.c_categorytype = c_categorytype;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getC_categorytype() {
        return c_categorytype;
    }

    public void setC_categorytype(String c_categorytype) {
        this.c_categorytype = c_categorytype;
    }
}
