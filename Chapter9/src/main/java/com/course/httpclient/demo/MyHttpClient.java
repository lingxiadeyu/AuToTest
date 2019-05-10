package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClient {

    //加入@Test注解
    @Test
    public void testHttpClient() throws IOException {

        //定义httpclient对象
        HttpClient httpClient1=new DefaultHttpClient();
        //HttpGet类，参数为要执行的url，实际测试中可以为接口地址
        HttpGet httpGet=new HttpGet("http://localhost:8889/getcookies");
        //用httpclient对象执行httpGet，并把返回结果存到httpresponse中
        HttpResponse httpResponse=httpClient1.execute(httpGet);
        //httpResponse的结果要调getEntity方法接收，同时需要转换成String类型才可以打印出来。
        String result= EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);
        ((DefaultHttpClient) httpClient1).close();


    }
}
