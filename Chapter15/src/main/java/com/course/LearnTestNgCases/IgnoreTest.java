package com.course.LearnTestNgCases;

import org.testng.annotations.Test;

public class IgnoreTest {
    @Test
    public void ignore1(){
        System.out.println("执行测试");
    }
    @Test(enabled = false)
    public void ignore2(){
        System.out.println("enable=false 忽略测试");
    }
    @Test(enabled = true)
    public void ignore3(){
        System.out.println("enable=true 执行测试");
    }
}
