package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.loginCase;
import com.course.utils.DatabaseUtil;
import com.course.utils.configFile;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {

    //加@BeforeTest注解，在用例执行前先获取接口的url
    @BeforeTest(groups = "loginTrue",description = "登录接口")
    public void beforeTest(){
        //获取接口地址
        TestConfig.loginuri = configFile.getUrl(InterfaceName.LOGIN);
        TestConfig.addUseruri = configFile.getUrl(InterfaceName.ADDUSER);
        TestConfig.getUserInfouri = configFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListuri = configFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.updateUserInfouri = configFile.getUrl(InterfaceName.UPDATEUSERINFO);

        TestConfig.defaultHttpClient = new DefaultHttpClient();
    }
    @Test(groups = "loginTrue", description = "登录成功的接口")
    public void LoginTrue() throws IOException {
        //定义sqlsession对象，从DatabaseUtil类中调getsqlSession方法，直接获取到sqlsession对象，可以执行sql语句，不需要配置数据库信息
        SqlSession sqlSession = DatabaseUtil.getsqlSession();
        //调selectone方法，通过mybatis中的sql语句查数据库表，并返回结果赋值给logincase对象
        //sqlstatement与SQLMapper.xml中的id一致，33是传的参数id
        loginCase loginCase = sqlSession.selectOne("logincase",33);
        //打印查询的结果，结果就是从数据库中查询到的结果
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginuri);
    }

    //调数据库表中第二行数据，可以认为是用户名和密码错误，登录失败的接口
    @Test(groups = "loginFalse",description = "登录失败的接口")
    public void LoginFalse() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getsqlSession();
        loginCase loginCase = sqlSession.selectOne("logincase",36);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginuri);
    }

}
