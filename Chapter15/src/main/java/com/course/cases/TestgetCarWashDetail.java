package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.ParamsName;
import com.course.utils.ConfigFile;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.*;

import org.json.JSONObject;

public class TestgetCarWashDetail {
    //获取商户id、商品id、skuid供下一个接口使用
    public static int merchantid;
    public static int goodsid;
    public static int skuid;
    public static String shopName;
    public static float salePrice;

    //参数也可以用map的形式传递
//    public static Map<String,Object> map;


    @Test(dependsOnGroups = "loginTrue",description = "测试洗车详情接口,uri中写入部分参数，其他参数从依赖接口中获取")
    public void testgetcarwashdetail() throws URISyntaxException, IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        //url要拼接参数
        URIBuilder uriBuidler = new URIBuilder(TestConfig.getCarWashDetailuri);
        //需要从其他类中依赖的参数值
        uriBuidler.setParameter("merchantId", String.valueOf(TestgetMerchantList.MerchantId));

        HttpGet httpGet = new HttpGet(uriBuidler.build());
        httpGet.setHeader("token",TestDologin.token);

        System.out.println(uriBuidler.build());
//        httpClient.setCookieStore(TestDologin.cookieStore);

        HttpResponse httpResponse = httpClient.execute(httpGet);
        String result = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);

        //获取商户id、商品id、skuid供下一个接口使用
        JSONObject resultobject = new JSONObject(result);
        JSONObject p2pdataobject = resultobject.getJSONObject("p2pdata");

        JSONObject merchantobject = p2pdataobject.getJSONObject("merchant");
        merchantid = merchantobject.getInt("id");
        shopName = merchantobject.getString("shopName");

        JSONObject goodsobject = p2pdataobject.getJSONObject("goodsInfo");
        goodsid = goodsobject.getInt("id");


        JSONArray skuDTOListarray = goodsobject.getJSONArray("skuDTOList");
        String skudtolistone = skuDTOListarray.get(0).toString();
        JSONObject skudtolistoneobject = new JSONObject(skudtolistone);
        skuid = skudtolistoneobject.getInt("id");
        salePrice = skudtolistoneobject.getFloat("salePrice");


        System.out.println("商户id是"+merchantid+"，商户名称是"+shopName+"，商品id是"+goodsid+"，skuid是"+skuid);

//        //参数也可以用map的形式传递给下一个接口
//        map = new HashMap<>();
//        map.put("sellerId",merchantobject.getInt("id"));
//        map.put("goodsId",goodsobject.getInt("id"));

    }

    @Test(dependsOnGroups = "loginTrue",description = "测试洗车详情接口，uri中不写入参数，手写参数")
    public void testgetcarwashdetailtwo() throws URISyntaxException, IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        URIBuilder uriBuilder = new URIBuilder(TestConfig.getCarWashDetailuritwouri);

//        //第一种写入参数的方法，定义一个List,类型是NameValuePair
//        List<NameValuePair> urlParameters = new ArrayList<>();
//        urlParameters.add(new BasicNameValuePair("merchantId", String.valueOf(TestgetMerchantList.MerchantId)));
//        urlParameters.add(new BasicNameValuePair("channel","5"));
//        uriBuilder.setParameters(urlParameters);

        //第二种写入参数的方法
        uriBuilder.setParameter("merchantId",String.valueOf(TestgetMerchantList.MerchantId));
        uriBuilder.setParameter("channel","5");


        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("token",TestDologin.token);

//        httpClient.setCookieStore(TestDologin.cookieStore);

        HttpResponse httpResponse = httpClient.execute(httpGet);
        String result = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);

    }
}
