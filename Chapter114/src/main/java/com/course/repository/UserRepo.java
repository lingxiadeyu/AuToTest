package com.course.repository;

import com.course.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/*
创建一个接口，继承JpaRepository类，User是创建的实体类
SpringBoot会自动将接口类自动注解到Spring容器中，不需要我们做任何配置
 */
//public interface UserRepo extends JpaRepository<User,Integer> {
//
//}

public interface UserRepo extends JpaRepository<User,Integer> {

}