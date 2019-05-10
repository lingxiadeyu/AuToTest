package com.course.LearnTestNgCases;

import org.testng.annotations.Test;

public class TestString {
    @Test
    public void teststring(){
        String content = "http://test-trade.zhidaohulian.com/pay/cashierDesk?payOrderNo=393048613635694592";
//        String payOrderNo = content;
//        System.out.println(payOrderNo);

        String str = "房估字(2014)第YPQD0006号";
        String jieguo = content.substring(61);
        int i = content.indexOf("=");
        System.out.println(jieguo);

    }
}
