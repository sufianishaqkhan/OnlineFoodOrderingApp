package com.usamaqadeer.freshveg.api.models;

import com.google.gson.annotations.SerializedName;

public class OrderDetailsModel {
    @SerializedName("od_id")
    public int od_id;
    @SerializedName("od_user_id")
    public int od_user_id;
    @SerializedName("od_pid")
    public int od_pid;
    @SerializedName("od_qty")
    public int od_qty;
    @SerializedName("od_delivered_loc")
    public String od_delivered_loc;
    @SerializedName("db_shiftend")
    public double od_price;

    public OrderDetailsModel(int od_id, int od_user_id, int od_pid, int od_qty, String od_delivered_loc, double od_price) {
        this.od_id = od_id;
        this.od_user_id = od_user_id;
        this.od_pid = od_pid;
        this.od_qty = od_qty;
        this.od_delivered_loc = od_delivered_loc;
        this.od_price = od_price;
    }

    public int getOd_id() {
        return od_id;
    }

    public void setOd_id(int od_id) {
        this.od_id = od_id;
    }

    public int getOd_user_id() {
        return od_user_id;
    }

    public void setOd_user_id(int od_user_id) {
        this.od_user_id = od_user_id;
    }

    public int getOd_pid() {
        return od_pid;
    }

    public void setOd_pid(int od_pid) {
        this.od_pid = od_pid;
    }

    public int getOd_qty() {
        return od_qty;
    }

    public void setOd_qty(int od_qty) {
        this.od_qty = od_qty;
    }

    public String getOd_delivered_loc() {
        return od_delivered_loc;
    }

    public void setOd_delivered_loc(String od_delivered_loc) {
        this.od_delivered_loc = od_delivered_loc;
    }

    public double getOd_price() {
        return od_price;
    }

    public void setOd_price(double od_price) {
        this.od_price = od_price;
    }
}
