package com.usamaqadeer.freshveg.api.models;

import com.google.gson.annotations.SerializedName;

public class OrderAssignsModel {
    @SerializedName("oa_id")
    public int oa_id;
    @SerializedName("u_name")
    public String u_name;
    @SerializedName("p_name")
    public String p_name;
    @SerializedName("od_qty")
    public int od_qty;
    @SerializedName("od_price")
    public String od_price;
    @SerializedName("oa_u_id")
    public int oa_u_id;
    @SerializedName("oa_db_id")
    public int oa_db_id;
    @SerializedName("oa_status")
    public String oa_status;

    public OrderAssignsModel(int oa_id, String u_name, String p_name, int od_qty, String od_price, int oa_u_id, int oa_db_id, String oa_status) {
        this.oa_id = oa_id;
        this.u_name = u_name;
        this.p_name = p_name;
        this.od_qty = od_qty;
        this.od_price = od_price;
        this.oa_u_id = oa_u_id;
        this.oa_db_id = oa_db_id;
        this.oa_status = oa_status;
    }

    public int getOa_id() {
        return oa_id;
    }

    public void setOa_id(int oa_id) {
        this.oa_id = oa_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public int getOd_qty() {
        return od_qty;
    }

    public void setOd_qty(int od_qty) {
        this.od_qty = od_qty;
    }

    public String getOd_price() {
        return od_price;
    }

    public void setOd_price(String od_price) {
        this.od_price = od_price;
    }

    public int getOa_u_id() {
        return oa_u_id;
    }

    public void setOa_u_id(int oa_u_id) {
        this.oa_u_id = oa_u_id;
    }

    public int getOa_db_id() {
        return oa_db_id;
    }

    public void setOa_db_id(int oa_db_id) {
        this.oa_db_id = oa_db_id;
    }

    public String getOa_status() {
        return oa_status;
    }

    public void setOa_status(String oa_status) {
        this.oa_status = oa_status;
    }
}
