package com.course.LearnTestNgCases.MultiThread;

import org.testng.annotations.Test;

/*
验证使用多线程
 */
public class MultiThreadOnAnnotation {

    //设置10个线程，3个线程池，用3个线程执行10遍测试用例,如果不设置threadPoolSize，就会默认用一个线程执行10遍，所以要设置一个线程池
    @Test(invocationCount = 10,threadPoolSize = 3)
    public void testMultiThreadOnAnnotation(){
        System.out.println("1");
        System.out.printf("Thread id: %s%n",Thread.currentThread().getId());
    }
}
