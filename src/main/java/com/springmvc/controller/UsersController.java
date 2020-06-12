package com.springmvc.controller;

import com.springmvc.entity.Users;
import com.springmvc.service.UserService;
import com.springmvc.utils.EncryptToMd5;
import jdk.nashorn.internal.scripts.JS;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ypl
 * @date 2020/5/19 - 21:51
 **/
@RequestMapping("/users/")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext.xml")
@Controller
public class UsersController {
    @Resource
    private UserService userService;
    //根据用户帐号删除用户信息
    //json格式{"id":"1"}
    Users user = new Users();

    @ResponseBody
    @RequestMapping(value = "deleteById",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public  boolean deleteById(@RequestBody JSONObject param){
        JSONObject jsonObject = JSONObject.fromObject(param);
        String id = jsonObject.getString("id");
        boolean result = userService.deleteById(id);
        return result;
    }

    //根据用户编号更新用户信息
    @ResponseBody
    @RequestMapping(value = "updateById",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public boolean updateById(@RequestBody JSONObject param){
        JSONObject jsonObject = JSONObject.fromObject(param);

        String passwordMd5 = EncryptToMd5.encryptToMd5(jsonObject.getString("password"));
        user.setId(jsonObject.getString("id"));
        user.setUsername(jsonObject.getString("username"));
        user.setPassword(passwordMd5);
        user.setPhone(jsonObject.getString("phone"));
        user.setUsertype(jsonObject.getString("usertype"));
        user.setComment(jsonObject.getString("comment"));
        boolean result = userService.updateById(user);
        return true;
    }

    //插入用户信息
    @ResponseBody
    @RequestMapping(value = "insertOne",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public boolean insertOne(@RequestBody JSONObject param){
        JSONObject jsonObject = JSONObject.fromObject(param);
        //先判断，用户是否存在
        String id = jsonObject.getString("id");
        String password = jsonObject.getString("password");
        List <Users> users = userService.selectById(id);
        if (users.isEmpty()){
            user.setId(id);
            user.setUsername(jsonObject.getString("username"));
            user.setPassword(EncryptToMd5.encryptToMd5(password));
            user.setPhone(jsonObject.getString("phone"));
            user.setUsertype(jsonObject.getString("usertype"));
            user.setComment(jsonObject.getString("comment"));
            System.out.println(user);
            boolean result = userService.insertOne(user);
            return true;
        } else{
            return false;
        }

    }
    @Test
    public void userTest(){
        Users user = new Users();
        user.setId("111");
        user.setUsername("admin");
        user.setPassword(EncryptToMd5.encryptToMd5("root"));
        user.setPhone("12345679888");
        user.setUsertype("1");
        user.setComment("管理员");
        boolean result = userService.insertOne(user);
        System.out.println(result);
    }
    //查询一条用户
    @ResponseBody
    @RequestMapping(value = "selectOne",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JSONObject selectOne(@RequestBody JSONObject param){
        JSONObject jsonObject = JSONObject.fromObject(param);
        JSONObject jsonObject1 = new JSONObject();
        user = userService.selectOne(jsonObject.getString("id"));
        jsonObject1.put("id",user.getId());
        jsonObject1.put("username",user.getUsername());
        jsonObject1.put("password",user.getPassword());
        jsonObject1.put("phone",user.getPhone());
        jsonObject1.put("usertype",user.getUsertype());
        jsonObject1.put("commenr",user.getComment());
        return jsonObject1;
    }
    @Test
    public void test1(){
        JSONArray jsonArray = new JSONArray();
        jsonArray = select();
        System.out.println(jsonArray);
    }
    //查询全部用户信息
    @ResponseBody
    @RequestMapping(value = "select",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JSONArray select(){
        JSONArray jsonArray = new JSONArray();
        List<Users> users = userService.select();
        for (int i = 0;i < users.size();i++){

            jsonArray.add(users.get(i));
        }
        return jsonArray;
    }
    @Test
    public void test(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","12");
        jsonObject.put("password","123456");
        boolean result = login(jsonObject);
        System.out.println(result);
    }
    @ResponseBody
    @RequestMapping(value = "login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public boolean login(@RequestBody JSONObject param){
        JSONObject jsonObject = JSONObject.fromObject(param);
        String id = jsonObject.getString("id");
        String password = EncryptToMd5.encryptToMd5(jsonObject.getString("password"));

        List<Users> users = userService.login(id,password);
        if (!users.isEmpty()){
            return true;
        }
        return  false;
    }
}