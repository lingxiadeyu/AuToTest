package com.course.LearnTestNgCases.Groups;

import org.testng.annotations.Test;

@Test(groups = "student")
public class groupclass2 {
    @Test
    public void testgroupclass2(){
        System.out.println("这是student分组下的测试用例2");
    }
}
