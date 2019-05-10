package com.course.LearnRestassuredCase;

import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestJenkins {

    @Test
    public void testjenkins() throws IOException {

//        //从数据库中获取数据值与接口返回的值进行比对。
//        SqlSession sqlSession = DatabaseUtil.getsqlsession();
//        int girlscounts = sqlSession.selectOne("getUserCount");
//
//        given()
//                .get("http://localhost:8085/demo/getuserlist")
//                .then()
//                .body(equalTo(String.valueOf(girlscounts)));

                given()
                .get("http://localhost:8095/demo/getuserlist")
                .then()
                .body(equalTo("22"));
        System.out.println("执行成功");

    }

    @Test
    public void testmultplication(){
        for (int i=1;i<10;i++){
            for (int j=1;j<i+1;j++){
                System.out.print(" "+i+"*"+j+"="+i*j);
                if (j == i){
                    System.out.println("");
                }

            }
        }
    }
}
