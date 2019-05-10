package com.course.LearnTestNgCases;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.testng.annotations.*;

/*
test注解
 */
public class BasicAnnotation {
    @Test
    public void testcaseone(){
        System.out.println("这是最基本的注解，用来把方法标记为测试的一部分");
    }
    @Test
    public void testcasetwo(){
        System.out.println("第二个测试方法");
    }
    @BeforeSuite
    public void beforesuite(){
        System.out.println("beforesuite测试套件，在BeforeClass类之前运行");
    }
    @AfterSuite
    public void aftersuite(){
        System.out.println("aftersuite测试套件，在Afterclass类之后运行");
    }
    @BeforeTest
    public void beforetest(){
        System.out.println("beforetest在测试之前运行");
    }
    @AfterTest
    public void aftertest(){
        System.out.println("aftertest在测试之后运行");
    }
    @BeforeClass
    public void beforeclass(){
        System.out.println("在类之前运行");
    }
    @AfterClass
    public void afterclass(){
        System.out.println("在类之后运行");
    }
    @BeforeMethod
    public void beforemethod(){
        System.out.println("测试方法之前运行");
    }
    @AfterMethod
    public void aftermethod(){
        System.out.println("测试方法之后运行");
    }

}
