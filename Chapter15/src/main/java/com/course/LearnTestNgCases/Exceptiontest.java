package com.course.LearnTestNgCases;

import org.testng.annotations.Test;

/*
异常测试，期望测试结果就是异常，用@Test(expectedExceptions = RuntimeException.class)注解使测试用例执行结果为正常
 */
public class Exceptiontest {

    @Test(expectedExceptions = RuntimeException.class)
    public void testExceptiontest(){
        System.out.println("执行时会有异常，预期结果就是希望有异常，所以测试用例会执行通过。本用例要抛出一个异常");


        //主动抛出一个异常或者制造一个异常
//        throw new RuntimeException();
        int a=10;
        int b=0;
        int c=a/b;//b为0，肯定会报异常。

    }
}
