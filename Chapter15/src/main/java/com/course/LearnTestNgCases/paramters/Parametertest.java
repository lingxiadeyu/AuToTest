package com.course.LearnTestNgCases.paramters;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test(groups = "student")
public class Parametertest {

    @Test
    @Parameters({"name","age"})
    public void testGetParameterfromXML(String name,int age){
        System.out.println("name="+name+",age="+age);
    }
}
