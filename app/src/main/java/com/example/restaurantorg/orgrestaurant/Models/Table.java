package com.example.restaurantorg.orgrestaurant.Models;


public class Table  {


    public String table_number;
    public int id;
    int imgid;

    public Table(String table_number, int imgid, int id) {
        this.id=id;
        this.table_number = table_number;
        this.imgid = imgid;
    }


    public String getCourse_name() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number=table_number;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }


}