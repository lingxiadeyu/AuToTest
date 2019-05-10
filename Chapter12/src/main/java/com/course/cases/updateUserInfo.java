package com.course.cases;

import com.course.model.updateUserInfoCase;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class updateUserInfo {

    @Test(dependsOnGroups = "loginTrue",description = "更新用户信息")
    public void updateUserInfo() throws IOException {
        //定义sqlsession
        SqlSession sqlSession = DatabaseUtil.getsqlSession();
        //执行sql语句并返回结果
        updateUserInfoCase updateUserInforesult = sqlSession.selectOne("updateUserInfoCase", 32);
        System.out.println(updateUserInforesult.toString());


    }

    @Test(dependsOnGroups = "loginTrue",description = "删除用户信息")
    public void deleteUserInfo() throws IOException {
        //定义sqlsession
        SqlSession sqlSession = DatabaseUtil.getsqlSession();
        //执行sql语句并返回结果
        updateUserInfoCase updateUserInforesult = sqlSession.selectOne("updateUserInfoCase", 32);
        System.out.println(updateUserInforesult.toString());


    }


}
