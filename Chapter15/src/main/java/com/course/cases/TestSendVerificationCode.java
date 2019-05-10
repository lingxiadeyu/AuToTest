package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.ParamsName;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestSendVerificationCode {
    //调验证码接口
    //从数据库中获取验证码返回到下一个接口中使用

    //设置全局变量
    public static int validate_code;

    @BeforeTest(groups = "loginTrue",description = "发送短信验证码接口")
    public void beforetest(){

        //获取所有接口的uri
        TestConfig.testsendVerificationCodeuri = ConfigFile.GetUrl(InterfaceName.SENDVERIFICATIONCODEURI);
        TestConfig.doLoginuri = ConfigFile.GetUrl(InterfaceName.DOLOGIN);
        TestConfig.testdoLoginuri = ConfigFile.GetUrl(InterfaceName.TESTDOLOGIN);
        TestConfig.getMerchantListuri = ConfigFile.GetUrl(InterfaceName.GETMERCHANTLIST);
        TestConfig.getCarWashDetailuri = ConfigFile.GetUrl(InterfaceName.GETCARWASHDETAIL);
        TestConfig.getCarWashDetailuritwouri = ConfigFile.GetUrl(InterfaceName.GETCARWASHDETAILURITWOURI);
        TestConfig.searchMerchantListuri = ConfigFile.GetUrl(InterfaceName.SEARCHMERCHANTLISTURI);
        TestConfig.loaduri = ConfigFile.GetUrl(InterfaceName.LOADURI);
        TestConfig.createuri = ConfigFile.GetUrl(InterfaceName.CREATEURI);

        //获取所有接口的参数值,doLoginParams接口是post入参json格式的，可以从配置文件中调，其他两个接口是get入参，可以直接拼接参数到url中
        TestConfig.doLoginParams = ConfigFile.GetParams(ParamsName.DOLOGINPARAMS);
        TestConfig.doLoginParamstwo = ConfigFile.GetParamswithMap(ParamsName.DOLOGINPARAMSTWOPARAMS);
//        TestConfig.getMerchantListParams = ConfigFile.GetStringParams(ParamsName.GETMERCHANTLISTPARAMS);
        TestConfig.getCarWashDetailParams = ConfigFile.GetParams(ParamsName.GETCARWASHDETAILPARAMS);
        TestConfig.loadParams = ConfigFile.GetParamswithMap(ParamsName.LOADPARAMS);
        TestConfig.createParams = ConfigFile.GetParamswithMap(ParamsName.CREATEPARAMS);
    }

    @Test(groups = "loginTrue",description = "发送短信验证码接口")
    public void getVerificationCode() throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(TestConfig.testsendVerificationCodeuri);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        String result = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);

        //从数据库查询验证码
        SqlSession sqlSession = DatabaseUtil.getsqlsession();
        validate_code = sqlSession.selectOne("getVerificationCode");
        System.out.println(validate_code);
    }


}
