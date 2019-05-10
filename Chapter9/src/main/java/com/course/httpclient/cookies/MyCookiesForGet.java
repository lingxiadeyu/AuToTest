package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.Arrays.*;

public class MyCookiesForGet {
    //定义一个String属性，用来存储获取到的url信息
    private String url;
    //定义一个ResourceBundle类的对象，用来从application.properties文件中获取配置信息
    private ResourceBundle resourceBundle;
    //定义一个cookiestore
    private CookieStore cookieStore;

    @BeforeTest
    public void BeforeTest(){
        //调用getBundle方法，告诉resourceBundle从哪个配置文件中获取配置信息，定义Locale.CHINA是中文信息
        resourceBundle=ResourceBundle.getBundle("application", Locale.CHINA);
        //传入test.url配置名称，获取配置信息并赋值给url
        url=resourceBundle.getString("test.url");
    }

    /*
    模拟httpclient请求，并返回结果
     */
    @Test(enabled = false)
    public void testGetCookies() throws IOException {
        //拼接url
        String uri=url+resourceBundle.getString("test.responsecookies");

        //httpclient请求接口获取返回结果并打印
        HttpClient httpClient=new DefaultHttpClient();
        HttpGet httpGet=new HttpGet(uri);
        HttpResponse response=httpClient.execute(httpGet);
        String result= EntityUtils.toString(response.getEntity());
        System.out.println(result);
        ((DefaultHttpClient) httpClient).close();


    }
        /*
    模拟获取接口中返回的cookie，本接口返回的cookie供下一个接口使用。
    如果有多个cookie，不论试哪种方法，只能获取到一个cookie值，可能cookie信息写入时写的不对。
     */

    @Test
    public void testGetAndResponseCookie() throws IOException {
        //拼接url
        String uri=url+resourceBundle.getString("test.responsecookies");


        //使用DefaultHttpClient类生成对象，不再使用HttpClient，这样才能获取到cookie
        DefaultHttpClient defaultHttpClient=new DefaultHttpClient();

        //使用getCookieStore()方法获取cookies信息，并赋值给cookieStore 全局变量
        cookieStore=defaultHttpClient.getCookieStore();

        //httpget请求
        HttpGet httpGet=new HttpGet(uri);
        HttpResponse httpResponse=defaultHttpClient.execute(httpGet);
        String result=EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);

        //获取cookies信息并打印出来
        List<Cookie> cookieStoreList=cookieStore.getCookies();

        //使用foreach循环返回cookie的name和value信息
        for(Cookie cookie : cookieStoreList){
            String name=cookie.getName();
            String value=cookie.getValue();
            System.out.println("cookiename:"+name+",cookievalue:"+value);
        }
        defaultHttpClient.close();


    }

    /*
    从上一个接口中获取cookie信息作为本接口的cookie
     */
    //使用dependsOnMethods = {"testGetAndResponseCookie"}参数，依赖testGetAndResponseCookie方法，也就是上一个方法返回的cookie信息
    @Test(dependsOnMethods = {"testGetAndResponseCookie"})
    public void testGetAndRequestCookie() throws IOException {
        String uri=url+resourceBundle.getString("test.getcookiesfromPI");

        DefaultHttpClient defaultHttpClient=new DefaultHttpClient();
        HttpGet httpGet=new HttpGet(uri);
        //设置cookie，作为本接口的请求中的cookie信息，this.cookieStore是指获取全局变量的值
        defaultHttpClient.setCookieStore(this.cookieStore);

        HttpResponse httpResponse=defaultHttpClient.execute(httpGet);

        //从返回结果中获取返回码
        int statuscode=httpResponse.getStatusLine().getStatusCode();
        if(statuscode==200){
            String result=EntityUtils.toString(httpResponse.getEntity());
            System.out.println(result);
        }else {
            System.out.println("接口执行失败");
        }
        defaultHttpClient.close();

    }

    /*
    模拟获取接口中返回的cookie，不论试哪种方法，只能获取到一个cookie值，可能cookie信息写入时写的不对。
     */
    @Test(enabled = false)
    public void testGetCookiesAndResponseCookie() throws IOException {
        //拼接url
//        String uri=url+resourceBundle.getString("getcookies");

        //使用DefaultHttpClient类生成对象，不再使用HttpClient，这样才能获取到cookie
        CloseableHttpClient defaultHttpClient = null;
        CookieStore cookieStore = new BasicCookieStore();
        defaultHttpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();

        HttpGet httpGet=new HttpGet("http://localhost:8889/get/cookies/two");
        HttpResponse httpResponse=defaultHttpClient.execute(httpGet);
        String result=EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);

        //获取cookies信息并打印出来
        List<Cookie> cookieStoreList=cookieStore.getCookies();

        for(Cookie cookie : cookieStoreList){
            String name=cookie.getName();
            String value=cookie.getValue();
            System.out.println("cookiename:"+name+",cookievalue:"+value);
        }
        defaultHttpClient.close();


    }
    /*
模拟获取接口中返回的cookie，不论试哪种方法，只能获取到一个cookie值，可能cookie信息写入时写的不对。
 */
    @Test(enabled = false)
    public void getCookie() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get=new HttpGet("http://localhost:8889/get/cookies/two");
        HttpClientContext context = HttpClientContext.create();
        try {
            CloseableHttpResponse response = httpClient.execute(get, context);
            try{
                System.out.println(">>>>>>cookies:");
                List<Cookie> cookieStoreList=context.getCookieStore().getCookies();
                for(Cookie cookie : cookieStoreList){
                    String name=cookie.getName();
                    String value=cookie.getValue();
                    System.out.println("cookiename:"+name+",cookievalue:"+value);
                }
            }
            finally {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        httpClient.close();
    }

}
