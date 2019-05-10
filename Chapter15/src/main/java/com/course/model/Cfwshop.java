package com.course.model;

public class Cfwshop {
    private int id;
    private String shop_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    @Override
    public String toString() {
        return "Cfwshop{" +
                "id=" + id +
                ", shop_name='" + shop_name + '\'' +
                '}';
    }
}
