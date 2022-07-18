package com.example.restaurantorg.orgrestaurant.Models;


import java.io.Serializable;

public class Item implements Serializable {

    protected String catid;
    private  boolean isChecked;
    private  boolean isChecked()
    {

        return  isChecked;
    }
    private  void setChecked(boolean checked)
    {

       isChecked=checked;
    }
    protected String name;

    protected String price;

    protected String description;
    public String id;
    // Discount e in procent



    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }
    public  Item()
    {

    }

    public Item(String id,String name, String price, String description, String catid) {
        this.id=id;
        this.catid = catid;
        this.name = name;
        this.price = price;
        this.description = description;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
