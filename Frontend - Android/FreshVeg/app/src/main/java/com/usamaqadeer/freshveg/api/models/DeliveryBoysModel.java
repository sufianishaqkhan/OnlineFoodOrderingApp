package com.usamaqadeer.freshveg.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class DeliveryBoysModel {
    @SerializedName("db_id")
    public int db_id;
    @SerializedName("db_name")
    public String db_name;
    @SerializedName("db_email")
    public String db_email;
    @SerializedName("db_password")
    public String db_password;
    @SerializedName("db_shiftstart")
    public String db_shiftstart;
    @SerializedName("db_shiftend")
    public String db_shiftend;
    @SerializedName("CreatedBy")
    public String CreatedBy;
    @SerializedName("CreatedDate")
    public String CreatedDate;

    public DeliveryBoysModel(int db_id, String db_name, String db_email, String db_password, String db_shiftstart, String db_shiftend, String createdBy, String createdDate) {
        this.db_id = db_id;
        this.db_name = db_name;
        this.db_email = db_email;
        this.db_password = db_password;
        this.db_shiftstart = db_shiftstart;
        this.db_shiftend = db_shiftend;
        CreatedBy = createdBy;
        CreatedDate = createdDate;
    }

    public int getDb_id() {
        return db_id;
    }

    public void setDb_id(int db_id) {
        this.db_id = db_id;
    }

    public String getDb_name() {
        return db_name;
    }

    public void setDb_name(String db_name) {
        this.db_name = db_name;
    }

    public String getDb_email() {
        return db_email;
    }

    public void setDb_email(String db_email) {
        this.db_email = db_email;
    }

    public String getDb_password() {
        return db_password;
    }

    public void setDb_password(String db_password) {
        this.db_password = db_password;
    }

    public String getDb_shiftstart() {
        return db_shiftstart;
    }

    public void setDb_shiftstart(String db_shiftstart) {
        this.db_shiftstart = db_shiftstart;
    }

    public String getDb_shiftend() {
        return db_shiftend;
    }

    public void setDb_shiftend(String db_shiftend) {
        this.db_shiftend = db_shiftend;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }
}
