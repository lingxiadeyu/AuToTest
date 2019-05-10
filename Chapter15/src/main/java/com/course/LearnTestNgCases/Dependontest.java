package com.course.LearnTestNgCases;

import org.testng.annotations.Test;

public class Dependontest {

    @Test
    public void testfirst(){
        System.out.println("testfirst方法执行通过后再执行下面的方法，执行失败不执行下面的方法");
    }

    @Test(dependsOnMethods = {"testfirst"})
    public void testsencond(){
        System.out.println("testsencond方法依赖testfirst方法");

    }

    @Test(groups = "testthird")
    public void testthird(){
        System.out.println("testthird方法执行通过后再执行下面依赖该组的方法，执行失败不执行下面的方法");
    }

    @Test(dependsOnGroups = "testthird")
    public void testfourth(){
        System.out.println("testfourth方法依赖testthird方法");
    }


}
