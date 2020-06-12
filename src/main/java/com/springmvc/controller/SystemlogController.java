package com.springmvc.controller;

import com.springmvc.dao.SystemlogMapper;
import net.sf.json.JSONArray;
import com.springmvc.entity.Systemlog;
import com.springmvc.service.SystemlogService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ypl
 * @date 2020/5/19 - 21:51
 **/
//系统日志
@RequestMapping("/systemlog/")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext.xml")
@Controller
public class SystemlogController {
    @Resource
    private SystemlogService systemlogService;
    @ResponseBody
    @RequestMapping(value = "selectByUserid",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JSONArray  selectByUserid(@RequestBody JSONObject param){
        JSONObject jsonObject = JSONObject.fromObject(param);
        JSONArray jsonArray = new JSONArray();
        List<Systemlog> systemlogs = systemlogService.select(jsonObject.getString("userid"));

        for(int i = 0;i < systemlogs.size();i++){
            jsonArray.add(systemlogs.get(i));
        }
        return jsonArray;
    }

    //删除
    @ResponseBody
    @RequestMapping(value = "deleteById",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public boolean deleteById(@RequestBody JSONObject param){
        JSONObject jsonObject = JSONObject.fromObject(param);
        boolean result = systemlogService.deleteById(jsonObject.getString("id"));
        return result;
    }
    @Test
    public void test()  {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","1");
        jsonObject.put("createtime","2010-1-1 00:00:00");
        jsonObject.put("userid","2");
        jsonObject.put("logname","用户操作");
        jsonObject.put("message","插入用户");
        try {
            boolean result = insertOne(jsonObject);
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    //插入
    @ResponseBody
    @RequestMapping(value = "insertOne",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public boolean insertOne(@RequestBody JSONObject param) throws ParseException {
        JSONObject jsonObject = JSONObject.fromObject(param);
        String date = jsonObject.getString("createtime");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date timeBegin =  simpleDateFormat.parse(date);
        List<Systemlog> systemlogs  = selectOne(jsonObject.getString("id"));
        if (systemlogs.isEmpty()){
            Systemlog systemlog = new Systemlog();
            systemlog.setId(jsonObject.getString("id"));
            systemlog.setCreatetime(timeBegin);
            systemlog.setUserid(jsonObject.getString("userid"));
            systemlog.setLogname(jsonObject.getString("logname"));
            systemlog.setMessage(jsonObject.getString("message"));
            boolean result = systemlogService.insertOne(systemlog);
            return result;
        }
        return false;
    }

    private List<Systemlog> selectOne(String id) {
        List<Systemlog> systemlogs = systemlogService.selectOne(id);
        return systemlogs;
    }
    @ResponseBody
    @RequestMapping(value = "selectAll",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public JSONArray selectAll(){
        JSONArray jsonArray = new JSONArray();
        List<Systemlog> systemlogs = systemlogService.selectAll();
        for (int i = 0;i < systemlogs.size();i++){
            jsonArray.add(systemlogs.get(i));
        }
        return jsonArray;
    }
    @Test
    public void test1(){
        JSONArray jsonArray = selectAll();
        System.out.println(jsonArray);
    }
}