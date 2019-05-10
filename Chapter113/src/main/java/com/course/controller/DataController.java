package com.course.controller;




import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.CoreConnectionPNames;
import org.mybatis.dao.Dao;
import org.mybatis.dao.condation.Cnd;
import org.mybatis.dao.condation.RawSql;
import org.mybatis.dao.selecte.Page;
import org.mybatis.dao.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.plugin.dom.core.CoreConstants;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    private Dao dao;

    @ResponseBody
    @RequestMapping("/test")
    public String test() throws IOException {
//        System.out.println("商品中心开始:");
//
//        List<Goods> goodsList = dao.selectList(Goods.class, Cnd.where(new RawSql("1=1")));
//        for(Goods g : goodsList){
//            if(g.getInstruction().startsWith("http")){
//                HttpClient httpClient = new DefaultHttpClient();
//                HttpGet httpget = new HttpGet(g.getInstruction().replace("http://img1.yixinfinance.com/chexian","http://static.zhidaohulian.com/hfw"));
//                HttpResponse response = httpClient.execute(httpget);
//                if(response.getStatusLine().getStatusCode() != 200){
//                    System.out.println("图片无效:"+g.getInstruction());
//                }else{
////                    System.out.println("图片正常:"+g.getInstruction());
//                }
//            }
//            if(g.getDetail()!=null){
//                for(String url : g.getDetail().split(",")){
//                    HttpClient httpClient = new DefaultHttpClient();
//                    HttpGet httpget = new HttpGet(url.replace("http://img1.yixinfinance.com/chexian","http://static.zhidaohulian.com/hfw"));
//                    HttpResponse response = httpClient.execute(httpget);
//                    if(response.getStatusLine().getStatusCode() != 200){
//                        System.out.println("图片无效:"+url);
//                    }else{
////                        System.out.println("图片正常:"+url);
//                    }
//                }
//            }
//        }
//
//        List<Sku> skuList = dao.selectList(Sku.class, Cnd.where(new RawSql("1=1")));
//
//        for(Sku sku : skuList){
//            for(String url : sku.getImages().split(",")){
//                HttpClient httpClient = new DefaultHttpClient();
//                HttpGet httpget = new HttpGet(url.replace("http://img1.yixinfinance.com/chexian","http://static.zhidaohulian.com/hfw"));
//                HttpResponse response = httpClient.execute(httpget);
//                if(response.getStatusLine().getStatusCode() != 200){
//                    System.out.println("图片无效:"+url);
//                }else{
////                        System.out.println("图片正常:"+url);
//                }
//            }
//        }
//        System.out.println("商品中心结束:");

        Page p = new Page(0,20);

        List<Shop> shopList = dao.selectList(Shop.class,Cnd.where(new RawSql("status = 0")),p);

        long count = p.getTotalCount();

        int SIZE = 10;

        int pageSize = (int)count / SIZE +1;
        System.out.println(pageSize);


        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(8);


        Map<Long, Integer> threadMap = new ConcurrentHashMap<>();


        for(int i = 0;i<pageSize;i++){
            int page = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {



                    System.out.println("开始第"+page+"页");
                    List<Shop> shopList = dao.selectList(Shop.class,Cnd.where(new RawSql("status = 0")),new Page(page,SIZE));
                    if(shopList!=null){
                        for(Shop shop :shopList ){
                            HttpClient httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager());

                            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,60000);
                            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,60000);
                            if(!StringUtils.isEmpty(shop.getBannerImgs())) {
                                String[] urls = shop.getBannerImgs().split(",");
                                for (String url : urls) {
                                    if (url.startsWith("http://static.zhidaohulian.com/hfw")) {
                                        HttpGet httpget = new HttpGet(url.replace("http://img1.yixinfinance.com/chexian", "http://static.zhidaohulian.com/hfw"));
                                        httpget.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
                                        HttpResponse response = null;
                                        try {
                                            response = httpClient.execute(httpget);
                                            if (response.getStatusLine().getStatusCode() != 200) {
                                                System.out.println("图片无效:" + shop.getShopName() + " " + shop.getId() + " " + url);
                                            }
                                        } catch (IOException e) {
                                            System.out.println("网络错误:" + e.getMessage());
                                            System.out.println("网络错误:" + url);
//                                        e.printStackTrace();
                                        }

                                    }
                                }
                            }

                            if(!StringUtils.isEmpty(shop.getShopIntro())&&shop.getShopIntro().startsWith("http")){
                                String[] urls = shop.getShopIntro().split(",");
                                for(String url:urls){
                                    if (url.startsWith("http://static.zhidaohulian.com/hfw")){
                                        HttpGet httpget = new HttpGet(url.replace("http://img1.yixinfinance.com/chexian","http://static.zhidaohulian.com/hfw"));
                                        HttpResponse response = null;
                                        try {
                                            response = httpClient.execute(httpget);
                                            if(response.getStatusLine().getStatusCode() != 200){
                                                System.out.println("图片无效:"+shop.getShopName()+" "+shop.getId()+" "+url);
                                            }
                                        } catch (IOException e) {
                                            System.out.println("网络错误:"+e.getMessage());
                                            System.out.println("网络错误:"+shop.getShopName()+" "+shop.getId()+" "+url);
//                                        e.printStackTrace();
                                        }
                                    }
                                }
                            }


                            if(!StringUtils.isEmpty(shop.getShopLogo())) {
                                String[] urls = shop.getShopLogo().split(",");
                                for (String url : urls) {
                                    if (url.startsWith("http://static.zhidaohulian.com/hfw")) {
                                        HttpGet httpget = new HttpGet(url.replace("http://img1.yixinfinance.com/chexian", "http://static.zhidaohulian.com/hfw"));
                                        httpget.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:63.0) Gecko/20100101 Firefox/63.0");
                                        HttpResponse response = null;
                                        try {
                                            response = httpClient.execute(httpget);
                                            if (response.getStatusLine().getStatusCode() != 200) {
                                                System.out.println("图片无效:" + shop.getShopName() + " " + shop.getId() + " " + url);
                                            }
                                        } catch (IOException e) {
                                            System.out.println("网络错误:" + e.getMessage());
                                            System.out.println("网络错误:" + shop.getShopName() + " " + shop.getId() + " " + url);
//                                        e.printStackTrace();
                                        }

                                    }
                                }
                            }


                        }
                    }
                    Integer size = threadMap.get(Thread.currentThread().getId());
                    if(size == null){
                        size = 0;
                    }
                    size = size+SIZE;

                    threadMap.put(Thread.currentThread().getId(),size);
                    if(size % 100 == 0){
                        System.out.println(Thread.currentThread().getName()+"线程休息3分钟");
                        try {
                            Thread.sleep(1000*60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    System.out.println("结束第"+page+"页");
                }
            });
        }

//        System.out.println("完成");
        return "success";
    }
}
