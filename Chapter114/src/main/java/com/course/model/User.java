package com.course.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity:@Table(name="") 表明这是一个实体类，一般和jpa配合着使用，如果实体类名称和数据库名称一致，@Table注解可以省略
//此注解代表要生成一个User数据表
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Integer id;  //id是主键，并且会自增，调接口时不需要传参数id，传其他参数就可以
    private String userName;
    private String password;
    private Integer age;
    private String sex;
    private Integer permission;
    private Integer isDelete;

    public User(){

    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
}
