package com.course.cases;

import org.apache.http.cookie.Cookie;
import org.json.JSONObject;
import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.ParamsName;
import com.course.utils.ConfigFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

/*
测试dologin接口
post入参也可以使用NameValuePair入参，参考https://www.cnblogs.com/hunt/p/7071053.html
 */
public class TestDologin {

    //设置全局变量cookieStore
    public static CookieStore cookieStore;

    //返回token值
    public static String token;

    /*
    从配置文件中获取全部的参数转换成String类型入参
     */
    @Test(groups = "mocotestlogin", description = "登录成功的接口")
    public void testdologin() throws IOException {
        System.out.println(TestConfig.doLoginuri);
        System.out.println(TestConfig.doLoginParams);

        DefaultHttpClient httpClient = new DefaultHttpClient();
        //该接口返回的有cookie，获取cookie，赋值给全局变量，下一个接口setcookiestore，下一个接口入参时使用cookie
        cookieStore = httpClient.getCookieStore();

        HttpPost httpPost = new HttpPost(TestConfig.doLoginuri);

        //设置请求头header
        httpPost.setHeader("Content-Type","application/json;charset=gbk");

        //传入参数,直接配置文件中取String类型的参数值也行，不用传object的也可以。
        StringEntity entity=new StringEntity(TestConfig.doLoginParams.toString(),"gbk");
        httpPost.setEntity(entity);

        //获取返回结果
        HttpResponse httpResponse = httpClient.execute(httpPost);
        String result = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);

    }
    /*
    使用map的方式入参，也可以使用数据依赖
     */

    @Test(groups = "loginTrue", description = "登录成功的接口")
    public void testdologintwo() throws IOException {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        //该接口返回的有cookie，获取cookie，赋值给全局变量，下一个接口setcookiestore，下一个接口入参时使用cookie
        //没有获取到cookie信息，因为这个接口不返回cookie
        cookieStore = httpClient.getCookieStore();
        List<Cookie> getcookielist =cookieStore.getCookies();
        for (Cookie cookie : getcookielist){
            String cookiename = cookie.getName();
            System.out.println("cookie是："+cookiename);
        }

        HttpPost httpPost = new HttpPost(TestConfig.testdoLoginuri);
        System.out.println(TestConfig.testdoLoginuri);

        //设置参数
        //使用json入参，入参是中文时不行。

        //设置请求头header
        httpPost.setHeader("Content-Type","application/json;charset=gbk");
        //传入参数
        Map<String,Object> map = TestConfig.doLoginParamstwo;
        map.put("validateCode",TestSendVerificationCode.validate_code);//也可以使用数据依赖传参数


        StringEntity entity=new StringEntity(map.toString(),"gbk");
        httpPost.setEntity(entity);

        //获取返回结果
        HttpResponse httpResponse = httpClient.execute(httpPost);
        //断言返回结果是否是200
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),200);
        //把结果转换成String类型打印出来
        String Responseresult = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(Responseresult);

        //把结果转换成jsonobject类型，获取token值
        JSONObject resultobject = new JSONObject(Responseresult);
        token = resultobject.getString("result");
        System.out.println(token);



    }

}
