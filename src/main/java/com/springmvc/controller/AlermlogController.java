package com.springmvc.controller;

import com.springmvc.entity.Alermlog;
import com.springmvc.entity.Equipment;
import com.springmvc.service.AlermlogService;
import com.springmvc.service.InformationService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ypl
 * @date 2020/6/7 - 21:03
 **/
@RequestMapping("/alermlog/")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext.xml")
@Controller
public class AlermlogController {
    @Resource
    private AlermlogService alermlogService;
    //查询所有报警日志
    @ResponseBody
    @RequestMapping(value = "selectAll",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JSONArray selectAll(){
        JSONArray jsonArray = new JSONArray();
        List<Alermlog> alermlogs = alermlogService.selectAll();
        for(int i = 0;i < alermlogs.size();i++){
            jsonArray.add(alermlogs.get(i));
        }
        return jsonArray;
    }
    //插入一条报警日志
    @ResponseBody
    @RequestMapping(value = "insert",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public boolean insert(JSONObject param){
        JSONObject jsonObject = JSONObject.fromObject(param);
        Alermlog alermlog = new Alermlog();
        Date date = new Date();
        alermlog.setCreatetime(date);
        alermlog.setComment(param.getString("comment"));
        boolean result = alermlogService.insert(alermlog);
        return result;
    }
    //根据编号删除报警日志
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public boolean delete(JSONObject param){
        JSONObject jsonObject = JSONObject.fromObject(param);
        boolean result = alermlogService.delete(param.getInt("id"));
        return true;
    }
    @Test
    public void test(){
        Date date = new Date();
        System.out.println(date);
    }

}