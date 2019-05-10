package com.course.LearnTestNgCases.Groups;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = "student")
public class groupclass1 {

    public void testgroupclass1(){
        System.out.println("这是student分组下的测试用例1");
    }
}
