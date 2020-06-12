package com.springmvc.controller;

import com.springmvc.entity.Information;
import com.springmvc.service.InformationService;
import com.springmvc.utils.ImportExcel;
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
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author ypl
 * @date 2020/5/19 - 21:52
 **/
@RequestMapping("/information/")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext.xml")
@Controller
public class InformationController {
    @Resource
    private InformationService informationService;

    //根据设备编号，查询最新消防栓监测信息
    @ResponseBody
    @RequestMapping(value = "selectNewDate",method = RequestMethod.POST,produces = "appllcation/json;charset=UTF-8")
    public JSONObject selectNewDate(@RequestBody JSONObject param){
        JSONObject jsonObject = JSONObject.fromObject(param);
        Information information = new Information();
        String table = "information"+jsonObject.get("equipmentid");
        System.out.println(table);
        information = informationService.selectNewDate(jsonObject.getString("equipmentid"),table);
        System.out.println(information);
        jsonObject.put("id",information.getId());
        jsonObject.put("datecreatetime",information.getDatecreatetime());
        jsonObject.put("equipment",information.getEquipmentid());
        jsonObject.put("pressure",information.getPressure());
        jsonObject.put("inclination",information.getInclination());
        jsonObject.put("state",information.getState());
        jsonObject.put("floemeter",information.getFlowmeter());
        jsonObject.put("temperature",information.getTemperature());
        jsonObject.put("battery",information.getBattery());
        jsonObject.put("contain1",information.getContain1());
        jsonObject.put("contain2",information.getContain2());
        jsonObject.put("contain3",information.getContain3());
        return jsonObject;
    }
    //根据设备编号，给出指定时间段，查询出在这个时间段数据
    @ResponseBody
    @RequestMapping(value = "selectSomeById",method = RequestMethod.POST,produces = "appllcation/json;charset=UTF-8")
    public JSONArray selectSomeById(@RequestBody JSONObject param) throws Exception {
        JSONObject jsonObject = JSONObject.fromObject(param);
        JSONArray jsonArray = new JSONArray();
        String equipmentid = jsonObject.getString("equipmentid");
        //first String to Date
        String time = jsonObject.getString("startTime");
        String time1 = jsonObject.getString("endTime");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date timeBegin =  simpleDateFormat.parse(time );
        Date timeEnd = simpleDateFormat.parse(time1);
        //Date to DateTime
        Date dateTime =  new Date(timeBegin.getTime());
        Date endTime =  new Date(timeEnd.getTime());
        List<Information> informations = informationService.selectSomeById(dateTime,endTime,equipmentid,"information"+equipmentid);
        //System.out.println(informations);
        for (int i = 0;i < informations.size();i++){
           //System.out.println(informations);
            jsonArray.add(informations.get(i));
        }
        return jsonArray;
    }
    //插入，这个接口与；预留给硬件
    public void insertOne(Information information){
        String table = "information"+information.getEquipmentid();
        int result = informationService.insertOne(information,table);
    }
    //插入多条数据
    public void insertAll(List<Information> informations){
        String table = "information"+informations.get(0).getEquipmentid();
        int result = informationService.insertAll(informations,table);
    }
    @Test
    public void test() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","1");
        jsonObject.put("startTime","2020-4-19 00:00:00");
        jsonObject.put("endTime","2021-2-2 00:00:00");
        boolean result = deleteAll(jsonObject);
        //System.out.println(jsonArray);
    }
    //根据设备编号，某个时间段，删除数据
    @ResponseBody
    @RequestMapping(value = "deleteAll",method = RequestMethod.POST,produces = "appllcation/json;charset=UTF-8")
    public boolean deleteAll(@RequestBody JSONObject param) throws ParseException {
        JSONObject jsonObject = JSONObject.fromObject(param);
        String equipmentid = jsonObject.getString("equipmentid");
        //first String to Date
        String time = jsonObject.getString("startTime");
        String time1 = jsonObject.getString("endTime");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date timeBegin =  simpleDateFormat.parse(time );
        Date timeEnd = simpleDateFormat.parse(time1);
        //Date to DateTime
        Date dateTime =  new Date(timeBegin.getTime());
        Date endTime =  new Date(timeEnd.getTime());
        boolean result = informationService.deleteAll(dateTime,endTime,equipmentid,"information"+equipmentid);
        return result;
    }
    //将数据导出到excal
    @ResponseBody
    @RequestMapping(value = "selectByIdToExcal",method = RequestMethod.POST,produces = "appllcation/json;charset=UTF-8")
    public boolean selectByIdToExcal(@RequestBody JSONObject param) throws ParseException {
        JSONObject jsonObject = JSONObject.fromObject(param);
        JSONArray jsonArray = new JSONArray();
        String equipmentid = jsonObject.getString("equipmentid");
        //first String to Date
        String time = jsonObject.getString("startTime");
        String time1 = jsonObject.getString("endTime");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date timeBegin =  simpleDateFormat.parse(time );
        Date timeEnd = simpleDateFormat.parse(time1);
        //Date to DateTime
        Date dateTime =  new Date(timeBegin.getTime());
        Date endTime =  new Date(timeEnd.getTime());
        List<Information> informations = informationService.selectSomeById(dateTime,endTime,equipmentid,"information"+equipmentid);
        //System.out.println(informations);
        return ImportExcel.exportExcel(informations);
    }
    //创建表
    @ResponseBody
    @RequestMapping(value = "createTable",method = RequestMethod.POST,produces = "appllcation/json;charset=UTF-8")
    public boolean createTable(@RequestBody JSONObject param){
        JSONObject jsonObject = JSONObject.fromObject(param);
        boolean result = informationService.createTable("information"+jsonObject.getString("id"));
        return true;
    }
    @Test
    public void test1(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","123");
        boolean result = createTable(jsonObject);
        System.out.println(result);
    }
}