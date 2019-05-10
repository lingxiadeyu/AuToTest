package com.course.cases;

import com.course.model.getUserInfoCase;
import com.course.model.getUserListCase;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "获取userid为25的信息")
    public void getUserInfo() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getsqlSession();
        getUserInfoCase getUserInforesult = sqlSession.selectOne("getUserInfoCase",35);
        System.out.println(getUserInforesult.toString());
    }
}
