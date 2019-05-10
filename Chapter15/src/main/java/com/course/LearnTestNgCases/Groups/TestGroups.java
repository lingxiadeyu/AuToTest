package com.course.LearnTestNgCases.Groups;

import org.testng.annotations.*;

public class TestGroups {

    @Test(groups = "server")
    public void testserver(){
        System.out.println("服务端测试用例");
    }
    @Test(groups = "client")
    public void testclient(){
        System.out.println("客户端测试用例");
    }

    @BeforeTest
    public void beforetest(){
        System.out.println("beforetest在测试之前运行");
    }
    @AfterTest
    public void aftertest(){
        System.out.println("aftertest在测试之后运行");
    }


    @BeforeGroups("server")
    public void beforeservergroups(){
        System.out.println("在服务端测试用例执行之前执行");
    }
    @AfterGroups("server")
    public void afterservergroups(){
        System.out.println("在服务端测试用例执行之后执行");
    }

    @BeforeGroups("client")
    public void beforeclientgroups(){
        System.out.println("在客户端测试用例执行之前执行");
    }
    @AfterGroups("client")
    public void afterclientgroups(){
        System.out.println("在客户端测试用例执行之后执行");
    }

}
