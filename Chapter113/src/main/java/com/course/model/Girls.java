package com.course.model;

import org.mybatis.dao.annotation.Table;

@Table("girls")
public class Girls {
    private int id;
    private int age;
    private String cupsize;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCupsize() {
        return cupsize;
    }

    public void setCupsize(String cupsize) {
        this.cupsize = cupsize;
    }
}
