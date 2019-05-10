package com.course.LearnTestNgCases.GroupsAndDataprovider;

import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/*
专门提供数据的类，在使用数据信息的地方用@Test(dataProvider = "data",dataProviderClass = class111.class)来引入
 */

public class class111 {

    @DataProvider(name = "data")
    public static Object[][] dataprovider(){
        Object[][] objects = new Object[][]{
                {"zhaowei",40,"kkk",180},
                {"suyoupeng",42,"jjj",170},
                {"linxinru",43,"ppp",160}
        };
        return objects;
    }

    @DataProvider(name="getmerchatidAndname")
    public static Iterator<Object[]> getmerchatidAndnameDataProvider() throws IOException {
        //定义Object[]类型的list
        List<Object[]> result=new ArrayList<Object[]>();
        //创建sqlsession的实例
        SqlSession session= DatabaseUtil.getsqlsession();
        //从数据库中获取数据信息
        List<Object> alldata=session.selectList("getmerchatidAndname");


       //从数据库中获取的数据类型是Object,该方法返回的类型是Object[]，所以需要做类型转换
        //用foreach循环对alldata中的每一条数据做形式转换，并存储到result中
        for (Object u : alldata) {
            //做一个形式转换
            result.add(new Object[] { u });
        }
        //然后result的迭代器类型的数据
        return result.iterator();


        //下面的代码与上面的代码功能类似就是做一个形式转换，从Object类型转换成Object[]类型
        //生成一个迭代器，对迭代器中的每一个数据做形式转换
        //迭代器的方法有hasNext()是否有值，有值为真，没值为假，it.next()获取具体的值
//        Iterator it=alldata.iterator();
//        while(it.hasNext()){
//            result.add(new Object[] { it.next() });
//        }
//        return  result.iterator();

    }

}
