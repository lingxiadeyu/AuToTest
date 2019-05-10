package com.course.controller;
import com.course.model.*;
import com.course.repository.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/demo")
@RestController
@Api(value = "/",description = "mybatis和mysql请求接口")
public class Demo {


    //注入SqlSessionTemplate类
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;




    //获取表中数据总数
    @RequestMapping(value = "/getuserlist",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户数量接口",httpMethod = "GET")
    public int getuserlist(){

        //getUserCount就是在mysql.xml中设置的id值
        return sqlSessionTemplate.selectOne("getUserCount");
    }

    //使用mybatis插入数据，post请求，postman中的请求参数就是girl实体类中的参数，要一致才行
    @RequestMapping(value = "/addUserUseMybatis",method = RequestMethod.POST)
    @ApiOperation(value = "用mybatis添加用户信息",httpMethod = "POST")
    public int addUserUseMybatis(@RequestBody Girls girls){
        //addUserUseMybatis就是在mysql.xml中设置的id值,该结果返回的是个int值
        int result = sqlSessionTemplate.insert("addUserUseMybatis",girls);
        return result;
    }

    //使用mybatis修改数据，post请求，传参为girl实体类中的参数值
    @RequestMapping(value = "/updataUsers",method = RequestMethod.POST)
    @ApiOperation(value = "修改用户信息",httpMethod = "POST")
    public int updataUsers(@RequestBody Girls girls){
        //updataUsers就是在mysql.xml中设置的id值
        return sqlSessionTemplate.update("updataUsers",girls);
    }

    //使用mybatis删除数据,接口中输入id值，删除该id中的数据
    @RequestMapping(value = "/deleteUsers",method = RequestMethod.GET)
    @ApiOperation(value = "删除用户",httpMethod = "GET")
    public int deleteUsers(@RequestParam("id") Integer id){
        //deleteUsers就是在mysql.xml中设置的id值
        return sqlSessionTemplate.delete("deleteUsers",id);
    }

    @Autowired
    UserRepo userRepo;
    @Autowired
    addUserCaseRepo addUserCaseRepo;
    @Autowired
     getUserInfoCaseRepo getUserInfoCaseRepo;
    @Autowired
    getUserListCaseRepo getUserListCaseRepo;
    @Autowired
    loginCaseRepo loginCaseRepo;
    @Autowired
    updateUserInfoCaseRepo updateUserInfoCaseRepo;
    @Autowired
    ImageUrlRepo imageUrlRepo;
    @Autowired
    BadImageUrlRepo badImageUrlRepo;
    @Autowired
    ElementDetailRepo elementDetailRepo;
    @Autowired
    namesRepo namesRepo;


    /*
    创建数据库表user，传参用form表单格式传参
     */
//    @RequestMapping(value = "/createUsertable",method = RequestMethod.POST)
//    public User createUsertable(@RequestParam("userName") String userName,
//                                @RequestParam("password") String password,
//                                @RequestParam("age") Integer age,
//                                @RequestParam("sex") String sex,
//                                @RequestParam("permission") Integer permission,
//                                @RequestParam("isDelete") Integer isDelete){
//        User user = new User();
////        user.setId(id);不需要传参
//        user.setUserName(userName);
//        user.setPassword(password);
//        user.setAge(age);
//        user.setSex(sex);
//        user.setPermission(permission);
//        user.setIsDelete(isDelete);
//        return userRepo.save(user);
//    }

    /*
    创建数据库表user，传参用json格式传参，节省了很多代码
     */
    @RequestMapping(value = "/createUsertable",method = RequestMethod.POST)
    public User createUsertable(@RequestBody User user){

        return userRepo.save(user);
    }
    /*
创建数据库表addUserCase，传参用json格式传参，节省了很多代码
 */
    @RequestMapping(value = "/createaddUserCasetable",method = RequestMethod.POST)
    public addUserCase createUsertable(@RequestBody addUserCase addUserCase){

        return addUserCaseRepo.save(addUserCase);
    }

    //启动服务后，只在postman中请求http://localhost:8085/demo/createaddUserCasetable的接口，其他的表都会自动创建。

    /*
创建数据库表getUserInfoCase，传参用json格式传参，节省了很多代码
*/
    @RequestMapping(value = "/creategetUserInfoCasetable",method = RequestMethod.POST)
    public getUserInfoCase createUsertable(@RequestBody getUserInfoCase getUserInfoCase){

        return getUserInfoCaseRepo.save(getUserInfoCase);
    }
    /*
创建数据库表getUserListCase，传参用json格式传参，节省了很多代码
*/
    @RequestMapping(value = "/creategetUserListCasetable",method = RequestMethod.POST)
    public getUserListCase createUsertable(@RequestBody getUserListCase getUserListCase){

        return getUserListCaseRepo.save(getUserListCase);
    }
    /*
创建数据库表loginCase，传参用json格式传参，节省了很多代码
*/
    @RequestMapping(value = "/createloginCasetable",method = RequestMethod.POST)
    public loginCase createUsertable(@RequestBody loginCase loginCase){

        return loginCaseRepo.save(loginCase);
    }
    /*
创建数据库表updateUserInfoCase，传参用json格式传参，节省了很多代码
*/
    @RequestMapping(value = "/createupdateUserInfoCasetable",method = RequestMethod.POST)
    public updateUserInfoCase createUsertable(@RequestBody updateUserInfoCase updateUserInfoCase){

        return updateUserInfoCaseRepo.save(updateUserInfoCase);
    }
    /*
创建数据库表ImageUrl，传参用json格式传参，节省了很多代码
*/
    @RequestMapping(value = "/createImageUrltable",method = RequestMethod.POST)
    public ImageUrl createImageUrltable(@RequestBody ImageUrl imageUrl){

        return imageUrlRepo.save(imageUrl);
    }

    /*
创建数据库表BadImageUrl，传参用json格式传参，节省了很多代码
*/
    @RequestMapping(value = "/createBadImageUrltable",method = RequestMethod.POST)
    public BadImageUrl createBadImageUrl(@RequestBody BadImageUrl badImageUrl){

        return badImageUrlRepo.save(badImageUrl);
    }
    /*
创建数据库表ElementDetail，传参用json格式传参
*/
    @RequestMapping(value = "/createElementDetailtable",method = RequestMethod.POST)
    public ElementDetail createElementDetail(@RequestBody ElementDetail elementDetail){

        return elementDetailRepo.save(elementDetail);
    }

    /*
创建数据库表ElementDetail，传参用json格式传参
*/
    @RequestMapping(value = "/createnamestable",method = RequestMethod.POST)
    public names createElementDetail(@RequestBody names elementDetail){

        return namesRepo.save(elementDetail);
    }





}
