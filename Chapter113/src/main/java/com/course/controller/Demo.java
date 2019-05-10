package com.course.controller;

import com.course.model.Girls;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.dao.Dao;
import org.mybatis.dao.condation.Cnd;
import org.mybatis.dao.condation.RawSql;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/demo")
@RestController
@Api(value = "/",description = "mybatis和mysql请求接口")
public class Demo {

    @Autowired
    private Dao dao;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;


    //获取表中数据总数
    @RequestMapping(value = "/getuserlist",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户数量接口",httpMethod = "GET")
    public int getuserlist(){
        //getUserCount就是在msql.xml中设置的id值
        return sqlSessionTemplate.selectOne("getUserCount");
    }

    //使用mybatis插入数据
    @RequestMapping(value = "/addUserUseMybatis",method = RequestMethod.POST)
    @ApiOperation(value = "用mybatis添加用户信息",httpMethod = "POST")
    public int addUserUseMybatis(@RequestBody Girls girls){
        int result = sqlSessionTemplate.insert("addUserUseMybatis",girls);
        return result;
    }

    //插入数据
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "添加用户信息",httpMethod = "POST")
    public int addUser(@RequestBody Girls girls){
        //直接调insert方法传入对象，通过接口传参传值
        dao.insert(girls);

        //直接更改数据表值
        dao.update(Girls.class,"age",18, Cnd.where("id",Cnd.EQ,5));
        return 0;
    }

    @RequestMapping(value = "/selectUser")
    public  List<Girls>  selectUser(){
        //返回数据表全部信息
        List<Girls> list = dao.selectList(Girls.class,Cnd.where(new RawSql("1=1")));
       return list;
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.GET)
    public void deleteUser(@RequestBody Girls girls){
        dao.delete(Girls.class,Cnd.where("id",Cnd.EQ,5));

    }

//根据某个id去删除怎么删除，怎么通过在调接口时传入参数做删除和更新

}
