package com.course.LearnTestNgCases;

import org.testng.annotations.Test;

/*
超时测试，测试中调接口可能会超时，导致调接口失败，通过设置超时时间，使用例执行通过。
 */
public class TimeOutTest {

    @Test(timeOut = 3000)//超时等待3000毫秒，就是3秒
    public void test1() throws InterruptedException {
        //设置一个线程，让线程休眠2000毫秒,等待时间大于休眠时间，所以用例会执行成功
        Thread.sleep(2000);
    }

    @Test(timeOut = 3000)
    public void test2() throws InterruptedException {
        //设置一个线程，让线程休眠4000毫秒，等待时间小于休眠时间，所以用例会执行失败
        Thread.sleep(4000);
    }

}
