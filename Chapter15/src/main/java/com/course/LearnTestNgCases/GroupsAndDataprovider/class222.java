package com.course.LearnTestNgCases.GroupsAndDataprovider;

import com.course.model.Cfwshop;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class class222 {

    //测试用例，从class111类中引入Object[][]类型的测试数据
    @Test(dataProvider = "data",dataProviderClass = class111.class)
    public void testdataprovider(String name,int age,String address,int height) throws IOException {
        System.out.println("通过DataProvider传过来的name是"+name+",age是"+age+",地址是"+address+",身高是"+height);
    }


    //测试用例，从class111类中引入Iterator类型的测试数据
    //需要创建一个Cfwshop的实体类，这样才能接收到具体的值
    @Test(dataProvider = "getmerchatidAndname",dataProviderClass = class111.class)
    public void testgetmerchatidAndname(Cfwshop cfwshop){
        System.out.println("商户id为:"+cfwshop.getId()+",商户名称为:"+cfwshop.getShop_name());

    }
}
