package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "get请求接口")
public class MyGetMethod {


    //模拟get请求
    @RequestMapping(value = "/useSpringBoot/get",method = RequestMethod.GET)
    @ApiOperation(value = "模拟get请求",httpMethod = "GET")
    public String get(){
        return "这是我的get请求的返回结果";
    }

    //模拟get请求返回cookie信息
    //HttpServletResponse类是一个存储器，可以把cookie等信息加入到response中返回
    @RequestMapping(value = "/useSpringBoot/getResponseCookies",method = RequestMethod.GET)
    @ApiOperation(value = "模拟get请求返回cookie信息",httpMethod = "GET")
    public String getResponseCookie(HttpServletResponse response){
        //设置cookie然后添加到response中，调接口时就会自动返回cookie
        Cookie cookie=new Cookie("login","true");
        response.addCookie(cookie);
        return "这是我的get请求返回cookie的请求结果";
    }

    //模拟get请求携带cookie信息
    //HttpServletRequest类是一个存储器，可以把cookie等信息加入到请求中去访问get请求
    @RequestMapping(value = "/useSpringBoot/getwithCookies",method = RequestMethod.GET)
    @ApiOperation(value = "模拟get请求携带cookie信息",httpMethod = "GET")
    public String getwithCookies(HttpServletRequest request){
        //get请求时需要手工输入或者从其他接口获取cookies，从get请求携带的cookie中获取cookie，并赋值给cookies数组
        Cookie[] cookies = request.getCookies();

        //判断cookies数组是否为空，如果为空返回错误，不为空判断cookies是不是获取到的cookies
        if(Objects.isNull(cookies)){
            return "必须携带cookie信息";
        }
        //foreach循环cookies，对象类型是cookie类，利用cookie类的getName和getValue方法判断cookies信息
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login") && cookie.getValue().equals("true")){
                return "这是一个携带cookie信息的get请求";
            }
        }
        return "cookies信息校验失败";

    }
    //模拟get请求携带参数
    @RequestMapping(value = "/useSpringBoot/getwithParam",method = RequestMethod.GET)
    @ApiOperation(value = "模拟get请求携带参数使用@RequestParam",httpMethod = "GET")
    public Map<String,String> getwithParam(@RequestParam("goodsid") String goodsid,
                                           @RequestParam("goodsdes") String goodsdes){

        Map<String,String> map=new HashMap<>();
        map.put("goodsid",goodsid);
        map.put("goodsdes",goodsdes);

        return map;

    }
    //模拟get请求携带参数 @PathVariable
    @RequestMapping(value = "/useSpringBoot/getwithParamtwo/{goodsid}/{goodsdes}",method = RequestMethod.GET)
    @ApiOperation(value = "模拟get请求携带参数使用@PathVariable",httpMethod = "GET")
    public Map<String,String> getwithParamtwo(@PathVariable("goodsid") String goodsid,
                                               @PathVariable("goodsdes") String goodsdes){
        Map<String,String> map=new HashMap<>();
        map.put("goodsid",goodsid);
        map.put("goodsdes",goodsdes);
        return map;
    }
}
