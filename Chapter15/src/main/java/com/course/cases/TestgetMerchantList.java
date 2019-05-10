package com.course.cases;

import com.course.config.TestConfig;
import com.course.utils.DatabaseUtil;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.http.client.CookieStore;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class TestgetMerchantList {

    //获取商户id供下一个接口使用
    public static int MerchantId;

    @Test(dependsOnGroups = "loginTrue",description = "获取洗车列表接口,获取第二页的数据")
    public void TestgetMerchantList() throws URISyntaxException, IOException {
        System.out.println(TestConfig.getMerchantListuri);

        DefaultHttpClient httpClient = new DefaultHttpClient();

        //参数已经拼接在url中
        HttpGet httpGet = new HttpGet(TestConfig.getMerchantListuri);



        //通过moco接口，可以使用setcookiestore方法从上一个接口获取token值
        httpClient.setCookieStore(TestDologin.cookieStore);

        //调取真实的接口只能设置header，传值token
//        httpGet.setHeader("token",TestDologin.token);

        HttpResponse response = httpClient.execute(httpGet);
        /*
        下面要验证几种断言方法：
        1、判断接口返回是否是200
        2、判断一页返回的门店列表有20条
        3、判断从第5位开始评分由大到小
        4、判断与数据库中获取数据是否一致
         */

        //1、判断接口返回是否是200
        Assert.assertEquals(response.getStatusLine().getStatusCode(),200,"接口返回200成功");

        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);

        JSONObject resultobject = new JSONObject(result);
        JSONObject p2pdataobject = resultobject.getJSONObject("p2pdata");
        JSONArray dataArray = p2pdataobject.getJSONArray("data");

        //2、判断一页返回的门店列表有20条
//        Assert.assertEquals(dataArray.length(),20,"一页返回门店列表有20条");
        //测试环境可能会有白名单商户，导致一页展示的商户个数可能会小于20条
        assert (dataArray.length() <= 20);



        List scorelist = new ArrayList();
        for (int i=0;i<dataArray.length();i++){
            String dataresult = dataArray.get(i).toString();
            JSONObject dataresultobject = new JSONObject(dataresult);
            scorelist.add(dataresultobject.getInt("score"));
        }
        //3、判断从第5位开始评分由大到小
        for (int j=4;j<scorelist.size()-1;j++){
            if ((Integer)scorelist.get(j) >= (Integer)scorelist.get(j+1)){
                System.out.println("比对成功");

            }else{
                System.out.println("比对不成功");
            }

        }

        String dataresult = dataArray.get(1).toString();
        JSONObject dataresultobject = new JSONObject(dataresult);
        MerchantId = dataresultobject.getInt("id");
        System.out.println(MerchantId);

        //4、判断与数据库中获取数据是否一致
        SqlSession sqlSession = DatabaseUtil.getsqlsession();
        int marchantid = sqlSession.selectOne("getmarchantid",MerchantId);
        Assert.assertEquals(marchantid,MerchantId);

    }

}
