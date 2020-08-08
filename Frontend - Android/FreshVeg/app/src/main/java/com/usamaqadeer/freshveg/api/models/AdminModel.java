package com.usamaqadeer.freshveg.api.models;

import com.google.gson.annotations.SerializedName;

public class AdminModel {
    @SerializedName("a_id")
    public int a_id;
    @SerializedName("a_name")
    public String a_name;
    @SerializedName("a_email")
    public String Name;
    @SerializedName("a_password")
    public String a_password;

    public AdminModel(int a_id, String a_name, String name, String a_password) {
        this.a_id = a_id;
        this.a_name = a_name;
        Name = name;
        this.a_password = a_password;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getA_password() {
        return a_password;
    }

    public void setA_password(String a_password) {
        this.a_password = a_password;
    }
}
