package com.course.model;

import lombok.Data;

//该代码中没有写get和set方法，是因为用了lombok可以自动省略get和set方法
@Data
public class User {
    private Integer id;  //id是主键，并且会自增，调接口时不需要传参数id，传其他参数就可以
    private String userName;
    private String password;
    private Integer age;
    private String sex;
    private Integer permission;
    private Integer isDelete;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", permission=" + permission +
                ", isDelete=" + isDelete +
                '}';
    }
}
