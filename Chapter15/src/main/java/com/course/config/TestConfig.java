package com.course.config;

import netscape.javascript.JSObject;

import java.util.Map;

public class TestConfig {
    //定义静态的url变量
    public static String testsendVerificationCodeuri;
    public static String doLoginuri;//moco的登录接口uri
    public static String testdoLoginuri;//测试环境登录接口uri
    public static String getMerchantListuri;
    public static String getCarWashDetailuri;
    public static String getCarWashDetailuritwouri;
    public static String searchMerchantListuri;
    public static String loaduri;
    public static String createuri;




    //定义静态的param变量
    public static  Object doLoginParams;
    public static String getMerchantListParams;
    public static  Object getCarWashDetailParams;
    public static Map<String,Object> doLoginParamstwo;
    public static Map<String,Object> loadParams;
    public static Map<String,Object> createParams;
}
