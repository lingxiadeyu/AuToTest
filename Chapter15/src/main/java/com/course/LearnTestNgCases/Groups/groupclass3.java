package com.course.LearnTestNgCases.Groups;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class groupclass3 {

    public void testgroupclass3(){
        System.out.println("这是teacher分组下的测试用例");
    }
}
