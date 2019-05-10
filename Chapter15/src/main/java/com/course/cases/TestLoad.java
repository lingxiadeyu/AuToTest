package com.course.cases;

import com.course.config.TestConfig;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestLoad {
    //获取userid
    public static String userId;


    @Test(dependsOnGroups = "loginTrue",description = "load下单前确认接口")
    public void testload() throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(TestConfig.loaduri);
        httpPost.setHeader("Content-Type","application/json;charset=gbk");

        Map<String,Object> map = TestConfig.loadParams;
        map.put("token",TestDologin.token);
        map.put("goodsId",TestgetCarWashDetail.goodsid);
        map.put("sellerId",TestgetCarWashDetail.merchantid);
        map.put("skuId",TestgetCarWashDetail.skuid);

        StringEntity stringEntity = new StringEntity(map.toString(),"gbk");
        httpPost.setEntity(stringEntity);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        String result = EntityUtils.toString(httpResponse.getEntity());

        System.out.println(result);

        JSONObject resultobject = new JSONObject(result);
        JSONObject resultsobject = resultobject.getJSONObject("result");
        Assert.assertEquals(resultsobject.getString("codeMsg"),"校验成功");

        JSONObject userDtoobject = resultsobject.getJSONObject("userDto");
        userId = userDtoobject.getString("userId");

        JSONObject merchantsVOobject = resultsobject.getJSONObject("merchantsVO");
        Assert.assertEquals(merchantsVOobject.getInt("sellerId"),TestgetCarWashDetail.merchantid);

        JSONObject itemParamobject = resultsobject.getJSONObject("itemParam");
        Assert.assertEquals(itemParamobject.getInt("itemId"),TestgetCarWashDetail.goodsid);
        Assert.assertEquals(itemParamobject.getInt("skuId"),TestgetCarWashDetail.skuid);





    }
}
