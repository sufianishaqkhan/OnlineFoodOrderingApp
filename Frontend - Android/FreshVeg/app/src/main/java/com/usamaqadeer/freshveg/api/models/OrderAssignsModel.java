package com.usamaqadeer.freshveg.api.models;

import com.google.gson.annotations.SerializedName;

public class OrderAssignsModel {
    @SerializedName("oa_id")
    public int oa_id;
    @SerializedName("oa_u_id")
    public int oa_u_id;
    @SerializedName("oa_db_id")
    public int oa_db_id;
    @SerializedName("oa_status")
    public String oa_status;

    public OrderAssignsModel(int oa_id, int oa_u_id, int oa_db_id, String oa_status) {
        this.oa_id = oa_id;
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
