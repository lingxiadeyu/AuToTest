package com.course.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BadImageUrl")
public class BadImageUrl {
    @Id
    @GeneratedValue
    private int id;
    private String badImageurl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBadImageurl() {
        return badImageurl;
    }

    public void setBadImageurl(String badImageurl) {
        this.badImageurl = badImageurl;
    }
}
