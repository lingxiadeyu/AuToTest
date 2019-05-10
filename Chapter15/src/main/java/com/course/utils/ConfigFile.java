package com.course.utils;

import com.alibaba.fastjson.JSON;
import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.ParamsName;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import java.util.*;
import org.json.JSONObject;
import com.alibaba.fastjson.JSON;
/*
从application.properties文件中获取url
 */
public class ConfigFile {
    //定义resourcebundle对象，调用getstring方法获取url地址
    //定义成私有全局静态变量，不需要声明类的对象就可以调用
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application", Locale.CHINA);

    //定义一个静态的方法获取url，传值为枚举的接口名称
    public static String GetUrl(InterfaceName name){
        //从application中获取本地moco接口的域名
//        String baseruri = resourceBundle.getString("mocouri");

        //从application中获取testsenduri接口的域名,发短信验证码和登录都会用到这个域名
        String testsendVerificationCodeuri = resourceBundle.getString("testsendVerificationCodeuri");

        //从application中获取实际测试接口的域名，除了短信验证码和登录都会用到这个域名
        String baseruri = resourceBundle.getString("testuri");

        //从application中获取创建订单接口的域名，
        String createorderuri = resourceBundle.getString("testcreateorder");

        //定义一个空的uri，下面会拼接url
        String apiurl = "";

        //如果传过来的内容是接口名称，则返回相应的接口地址
        if(name == InterfaceName.DOLOGIN){
            apiurl = resourceBundle.getString("doLoginuri");
            return baseruri+apiurl;
        }
        //测试环境的登录接口地址
        if (name == InterfaceName.TESTDOLOGIN){
            apiurl = resourceBundle.getString("doLoginuri");
            return testsendVerificationCodeuri+apiurl;
        }
        if(name == InterfaceName.GETMERCHANTLIST){
            apiurl = resourceBundle.getString("getMerchantListuri");
            return baseruri+apiurl;
        }
        if(name == InterfaceName.GETCARWASHDETAIL){
            apiurl = resourceBundle.getString("getCarWashDetailuri");
            return baseruri+apiurl;
        }
        if(name == InterfaceName.GETCARWASHDETAILURITWOURI){
            apiurl = resourceBundle.getString("getCarWashDetailuritwouri");
            return baseruri+apiurl;
        }
        if (name == InterfaceName.SENDVERIFICATIONCODEURI){
            apiurl = resourceBundle.getString("sendVerificationCodeuri");
            return testsendVerificationCodeuri+apiurl;
        }
        if (name == InterfaceName.SEARCHMERCHANTLISTURI){
            apiurl = resourceBundle.getString("searchMerchantListuri");
            return baseruri+apiurl;
        }
        if (name == InterfaceName.LOADURI){
            apiurl = resourceBundle.getString("loaduri");
            return createorderuri+apiurl;
        }
        if (name == InterfaceName.CREATEURI){
            apiurl = resourceBundle.getString("createuri");
            return createorderuri+apiurl;
        }
        return null;

    }

    //定义一个静态的方法，获取post接口的参数值，返回类型是Object类型
    public static Object GetParams(ParamsName name){
        if (name.equals(ParamsName.DOLOGINPARAMS)){
            Object doLoginParams = resourceBundle.getObject("doLoginParams");
            return doLoginParams;
        }
        return null;
    }
    //定义一个静态的方法，获取post接口的参数值，返回类型为map，这样有些参数可以依赖其他的接口，或者手动填写参数
    public static Map<String,Object> GetParamswithMap(ParamsName name){
        if (name == ParamsName.DOLOGINPARAMSTWOPARAMS){
            String doLoginParams = resourceBundle.getString("doLoginParamstwo");
            return JSON.parseObject(doLoginParams);
        }
        if (name == ParamsName.LOADPARAMS){
            String loadParams = resourceBundle.getString("loadParams");
            return JSON.parseObject(loadParams);
        }
        if (name == ParamsName.CREATEPARAMS){
            String createParams = resourceBundle.getString("createParams");
            return JSON.parseObject(createParams);
        }
        return null;

    }

    //本类中没有写获取get接口的参数值，是因为get接口的参数值可以放到uri中获取，或者是拼接到uri中。


}
