package com.course.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "getUserInfoCase")
public class getUserInfoCase {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private String expected;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }
}
