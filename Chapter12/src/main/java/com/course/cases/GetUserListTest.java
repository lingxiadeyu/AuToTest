package com.course.cases;

import com.course.model.getUserListCase;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserListTest {

    @Test(dependsOnGroups = "loginTrue",description = "获取性别为0：男的信息")
    public void getUserList() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getsqlSession();
        getUserListCase getUserListresult = sqlSession.selectOne("getuserlistcase",34);
        System.out.println(getUserListresult.toString());
    }
}
