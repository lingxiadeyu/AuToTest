package com.course.LearnTestNgCases.paramters;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class Dataprovidertest {
    //数据方法
    @DataProvider(name = "data")
    public Object[][] dataprovider(){
        Object[][] objects = new Object[][]{
                {"zhaowei",40,"kkk",180},
                {"suyoupeng",42,"jjj",170},
                {"linxinru",43,"ppp",160}
        };
        return objects;
    }

    //测试用例
    @Test(dataProvider = "data")
    public void testdataprovider(String name,int age,String address,int height){
        System.out.println("通过DataProvider传过来的name是"+name+",age是"+age+",地址是"+address+",身高是"+height);
    }

    //数据方法二,根据不同的方法传不同的值进来
    @DataProvider(name = "datatwo")
    public Object[][] dataprovidertwo(Method method){
        Object[][] result = null;
        if (method.getName().equals("testdologin")){
             result = new Object[][]{
                    {"18600228000","123456"}
            };
        }else if (method.getName().equals("testgetmerchantlist")){
            result = new Object[][]{
                    {38159,"秦小姐洗车门店","北京市通州区"}
            };
        }
        return result;

    }

    @Test(dataProvider = "datatwo")
    public void testdologin(String username,String password){
        System.out.println("登录成功，用户名是"+username+",密码是"+password);
    }
    @Test(dataProvider = "datatwo")
    public void testgetmerchantlist(int merchantid,String merchantname,String merchantaddress){
        System.out.println("查询商户列表成功，商户id是"+merchantid+",商户名称是"+merchantname+",商户地址是"+merchantaddress);
    }












}
