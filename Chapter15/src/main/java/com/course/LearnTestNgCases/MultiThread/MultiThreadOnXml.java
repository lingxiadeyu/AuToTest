package com.course.LearnTestNgCases.MultiThread;

import org.testng.annotations.Test;

public class MultiThreadOnXml {
    @Test
    public void test1(){
        System.out.printf("MultiThreadOnXml类  Thread Id : %s%n",Thread.currentThread().getId());
    }

    @Test
    public void test2(){
        System.out.printf("MultiThreadOnXml类  Thread Id : %s%n",Thread.currentThread().getId());
    }

    @Test
    public void test3(){
        System.out.printf("MultiThreadOnXml类  Thread Id : %s%n",Thread.currentThread().getId());
    }
}
