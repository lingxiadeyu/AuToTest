package com.course.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DatabaseUtil {
    public static SqlSession getsqlsession() throws IOException {
        //本地数据库配置
            Reader reader = Resources.getResourceAsReader("databaseConfig.xml");
        //测试环境数据库配置
//            Reader reader = Resources.getResourceAsReader("TestdatabaseConfig.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession sqlSession = factory.openSession();
            return sqlSession;
    }
}
