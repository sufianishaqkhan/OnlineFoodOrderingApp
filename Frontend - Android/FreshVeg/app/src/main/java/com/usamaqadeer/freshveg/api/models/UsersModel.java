package com.usamaqadeer.freshveg.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UsersModel {
    @SerializedName("u_id")
    public int u_id;
    @SerializedName("u_name")
    public String u_name;
    @SerializedName("u_email")
    public String u_email;
    @SerializedName("u_pass")
    public String u_pass;
    @SerializedName("CreatedDate")
    public String CreatedDate;

    public UsersModel(int u_id, String u_name, String u_email, String u_pass, String createdDate) {
        this.u_id = u_id;
        this.u_name = u_name;
        this.u_email = u_email;
        this.u_pass = u_pass;
        CreatedDate = createdDate;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_pass() {
        return u_pass;
    }

    public void setU_pass(String u_pass) {
        this.u_pass = u_pass;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }
}
