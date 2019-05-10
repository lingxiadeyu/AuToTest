package com.course.LearnTestNgCases.Suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/*
测试套件的配置
 */
public class SuiteConfig {

    @BeforeSuite
    public void beforesuite(){
        System.out.println("在测试套件前执行");
    }

    @AfterSuite
    public void aftersuite(){
        System.out.println("在测试套件后执行");
    }
}
