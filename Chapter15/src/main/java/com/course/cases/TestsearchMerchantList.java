package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.URISyntaxException;

/*
1、验证参数中有中文
 */
public class TestsearchMerchantList {


    @Test(dependsOnGroups = "logintrue",description = "搜索门店接口")
    public void testsearchMerchantList() throws URISyntaxException, IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        URIBuilder builder = new URIBuilder(TestConfig.searchMerchantListuri);
        builder.setParameter("searchContent","秦振霞");
        HttpGet httpGet = new HttpGet(builder.build());

        HttpResponse httpResponse = httpClient.execute(httpGet);
        String result = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);


    }

}
