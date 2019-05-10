package com.course.cases;

import com.course.config.TestConfig;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class TestCreate {

    @Test(dependsOnGroups = "loginTrue",description = "创建订单")
    public void testcreate() throws IOException {
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(TestConfig.createuri);
        httpPost.setHeader("Content-Type","application/json");

        Map<String,Object> map = TestConfig.createParams;
        map.put("token",TestDologin.token);
        map.put("userId",TestLoad.userId);
        map.put("sellerName",TestgetCarWashDetail.shopName);
        map.put("sellerId",TestgetCarWashDetail.merchantid);
        map.put("goodsId",TestgetCarWashDetail.goodsid);
        map.put("skuId",TestgetCarWashDetail.skuid);
        map.put("shouldPay",TestgetCarWashDetail.salePrice);
        map.put("totalSum",TestgetCarWashDetail.salePrice);
        map.put("orderSum",TestgetCarWashDetail.salePrice);

        //参数可以用map的形式传递。
//        Map<String,Object> map1 = TestgetCarWashDetail.map;
//        Map<String,Object> map3 = new HashMap<>();
//        map3.putAll(map);
//        map3.putAll(map1);


        StringEntity stringEntity = new StringEntity(map.toString(),"utf-8");
        httpPost.setEntity(stringEntity);

        HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
        String result = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);

        JSONObject resultreponseobject = new JSONObject(result);
        JSONObject resultobject = resultreponseobject.getJSONObject("result");

        String order_id = resultobject.get("orderId").toString();
        System.out.println(order_id);
        String resultContent = resultobject.getString("resultContent");
        String pay_order_no = resultContent.substring(resultContent.indexOf("=")+1);
        System.out.println(pay_order_no);

        SqlSession sqlSession = DatabaseUtil.getsqlsession();
        int createorder = sqlSession.selectOne("createorder",order_id);
        System.out.println(createorder);
        Assert.assertEquals(createorder,1);

        int createpayorder = sqlSession.selectOne("createpayorder",pay_order_no);
        System.out.println(createpayorder);
        Assert.assertEquals(createpayorder,1);








    }
}
