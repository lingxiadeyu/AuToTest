package com.course.cases;

import com.course.model.addUserCase;
import com.course.model.getUserListCase;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {

    @Test(dependsOnGroups = "loginTrue",description = "添加用户的信息")
    public void AddUser() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getsqlSession();
        addUserCase addUserresult = sqlSession.selectOne("addUserCase",30);
        System.out.println(addUserresult.toString());
    }
}
