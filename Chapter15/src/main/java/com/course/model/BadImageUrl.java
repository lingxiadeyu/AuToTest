package com.course.model;




public class BadImageUrl {

    private String id;
    private String badImageurl;

    public BadImageUrl(String id, String badImageurl) {
        this.id = id;
        this.badImageurl = badImageurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBadImageurl() {
        return badImageurl;
    }

    public void setBadImageurl(String badImageurl) {
        this.badImageurl = badImageurl;
    }
}
