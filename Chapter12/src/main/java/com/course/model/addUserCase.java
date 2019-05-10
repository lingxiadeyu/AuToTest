package com.course.model;

import lombok.Data;

@Data
public class addUserCase {
    private Integer id;
    private String userName;
    private String password;
    private Integer sex;//0是男，1是女
    private Integer age;
    private Integer permission;
    private Integer isDelete;
    private String expected;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }
}
