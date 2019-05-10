package com.course.cases;

import com.course.model.BadImageUrl;
import com.course.model.ImageUrl;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;
import org.testng.collections.CollectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestGetImageUrl {

    @Test
    public void getbsActivityImg() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getsqlsession();
        List<String> list = sqlSession.selectList("getbsActivityImg");
        for (String imgurl : list){
            System.out.println();
        }
    }


    @Test
    public void getImageurl() throws IOException {
        //调DatabaseUtil的getsqlsession()方法，返回类型是sqlsession
        SqlSession sqlSession = DatabaseUtil.getsqlsession();
        //调sqlsession的selectone方法，参数值是在SQLMapper.xml中写的sql语句的id,37是数据库中记录id

        ImageUrl KKK = sqlSession.selectOne("GetImageUrlcase",37);
        System.out.println(KKK.getUrl());



        //获取url总数
        int imageUrlnums = sqlSession.selectOne("GetImageUrlnums");
        //分页，每页2条，总共pagenum页
        int pageSize = 3;//每页处理2条
        //如果pagenum是小数，则处理页数要加1
        int count = imageUrlnums/pageSize+1;
        System.out.println(count);
        //(int)Math.ceil((double)imageUrlnums/pageSize);//总共pagenum页
        //最后一页条数
//        int lastpagenum = imageUrlnums-(pagenum-1)*pageSize;

//        System.out.println(pagenum);//打印总页数
//        System.out.println(lastpagenum);//打印最后一页的条数

        Map<String,Object> param = new HashMap<>();
        param.put("pageSize",pageSize);
        for(int i = 0;i<imageUrlnums;i=i+3) {
            param.put("pageNumber",i);
            List<Map<String,Object>> resultList = sqlSession.selectList("GetImageUrlList",param);

            if(resultList!=null) {

                for(Map<String,Object> m : resultList){
                    try {
                        DefaultHttpClient httpClient = new DefaultHttpClient();
                        HttpGet httpGet = new HttpGet(m.get("url").toString());
                        HttpResponse response = httpClient.execute(httpGet);
                        if (response.getStatusLine().getStatusCode() == 200) {
                            System.out.println("图片存在,id为：" + m.get("id")+" ,图片url: "+ m.get("url").toString());
                        } else {
                            System.out.println("图片不存在,id为：" +m.get("id")+" ,图片url: "+ m.get("url").toString());
                        }
                    }catch (Exception e){
                        System.out.println("网络异常,id为: " +m.get("id")+" ,图片url: "+ m.get("url").toString());
//                        BadImageUrl badImageUrl = new BadImageUrl(m.get("id").toString(),m.get("url").toString());
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",m.get("id"));
                        map.put("badImageurl",m.get("url"));
                        sqlSession.insert("addImageUrltwo",map);
                    }
                }
            }

        }


//        int secondpageid = 37;//初始id值，外层循环会用到
//        int initialid = 37;//初始id值，内存循环会用到
//        for (int j=pagenum;j>0;j--){
//            for (int k=initialid;k<secondpageid+pageSize;k++){
//                ImageUrl imageUrl = sqlSession.selectOne("GetImageUrlcase",k);
//                System.out.println(imageUrl.toString());
//                DefaultHttpClient httpClient = new DefaultHttpClient();
//                HttpGet httpGet = new HttpGet(imageUrl.getUrl());
//                HttpResponse response = httpClient.execute(httpGet);
//                if (response.getStatusLine().getStatusCode() != 200){
//                    System.out.println("url访问不成功");
//                    sqlSession.insert("addImageUrl");
//                    //会报连接失败，怎么处理
//
//                }else {
//                    System.out.println("url访问成功");
//                }
//
//                initialid++;
//            }
//            System.out.println("j的值是"+j);
//
//            if (j == 2){
//                secondpageid = secondpageid+lastpagenum;
//                System.out.println("1的值是"+secondpageid);
//            }else {
//                secondpageid = secondpageid+pageSize;
//                System.out.println("2的值是"+secondpageid);
//            }


//        }










    }
}
