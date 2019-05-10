package com.course.httpclient.cookies;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    //定义url存储请求地址
    private String url;
    //定义resourcebundle 从配置文件中获取配置新
    private ResourceBundle resourceBundle;
    //设置cookiestore全局变量
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
    public void testpostmethod() throws IOException {
        String uri=url+resourceBundle.getString("test.postwithcookiesusejson");

        //生成httpclient对象
        DefaultHttpClient defaultHttpClient=new DefaultHttpClient();

        //创建post请求
        HttpPost httpPost=new HttpPost(uri);

        //使用json入参，入参是中文时不行。
        JSONObject param=new JSONObject();
        param.put("name","qinzhenxia");
        param.put("age","28");

        //设置请求头header
        httpPost.setHeader("Content-Type","application/json;charset=gbk");
        //传入参数
        StringEntity entity=new StringEntity(param.toString(),"gbk");
        httpPost.setEntity(entity);

        //设置cookie是用defaultHttpClient调setCookieStore方法，不是用httppost调
        defaultHttpClient.setCookieStore(this.cookieStore);

        //执行请求并接收返回结果
        HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
        String result=EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);


        //判断返回code值
        int statuscode=httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statuscode,200);

        //获取返回结果并转换为jsonobject格式，然后获取某个具体的值通过断言判断
        JSONObject resultjson=new JSONObject(result);
        String msg =resultjson.getString("msg");
        //判断msg等于success
        Assert.assertEquals(msg,"success");


        JSONObject p2pdata=resultjson.getJSONObject("p2pdata");

        String address=p2pdata.getString("address");
        System.out.println(address);
        Assert.assertEquals(address,"北三环");

    }

}
