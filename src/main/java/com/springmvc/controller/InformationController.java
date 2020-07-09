package com.springmvc.controller;

import com.springmvc.entity.Alermlog;
import com.springmvc.entity.Information;
import com.springmvc.entity.Systemlog;
import com.springmvc.interceptor.ReadVarchar;
import com.springmvc.service.AlermlogService;
import com.springmvc.service.InformationService;
import com.springmvc.utils.ImportExcel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
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
    private AlermlogService alermlogService;
    @Resource
    private InformationService informationService;

    //根据设备编号，查询最新消防栓监测信息
    @ResponseBody
    @RequestMapping(value = "selectNewDate",method = RequestMethod.POST,produces = "appllcation/json;charset=UTF-8")
    public JSONObject selectNewDate(@RequestBody JSONObject param){
        JSONObject jsonObject = JSONObject.fromObject(param);
        Information information = new Information();
        String table = "information"+jsonObject.getString("equipmentid");

        information = informationService.selectNewDate(jsonObject.getString("equipmentid"),table);

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
        jsonObject.put("devicename",information.getEquipment().getDevicename());
        jsonObject.put("version",information.getEquipment().getVersion());
        jsonObject.put("devicetype",information.getEquipment().getDevicetype());
        jsonObject.put("nbiot_Gprs",information.getEquipment().getNbiot_Gprs());
        jsonObject.put("positions",information.getEquipment().getPositions());
        jsonObject.put("state",information.getEquipment().getState());
        jsonObject.put("serviceman1",information.getEquipment().getServiceman1());
        jsonObject.put("serviceman2",information.getEquipment().getServiceman2());
        jsonObject.put("address",information.getEquipment().getAddress());
        jsonObject.put("comment",information.getEquipment().getComment());
        return jsonObject;
    }

    //根据设备编号，给出指定时间段，查询出在这个时间段数据
    @ResponseBody
    @RequestMapping(value = "selectSomeById",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
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
    @ResponseBody
    @RequestMapping(
            value = {"insertOne"},
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"}
    )
    public String insertOne(@RequestParam String msg) throws Exception {

            //boolean re = true;
            Information information = ReadVarchar.readVarchar(msg);
            System.out.println(information);
            String table = "information"+information.getEquipmentid();
            boolean re = ReadVarchar.adjustVarchar(information);
            System.out.println(re);
            if (re){
                int result = informationService.insertOne(information,table);
                return "插入成功";
            }else {
                Alermlog alermlog = new Alermlog();
                Date date = new Date();
                alermlog.setCreatetime(date);
                alermlog.setComment("设备导入数据异常");
                boolean res = alermlogService.insert(alermlog);
                return "插入失败";
            }


    }
    //插入，这个接口与；预留给硬件
    @ResponseBody
    @RequestMapping(
            value = {"insertOnes"},
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"}
    )
    public String insertOnes(@RequestParam String msg) throws Exception {
        Date start = new Date();
        boolean result = informationService.insertOnes(msg,start);
        if (result){
            return "1";
        }else {
            return "0";
        }
    }
    //插入，这个接口与；预留给硬件
    @ResponseBody
    @RequestMapping(
            value = {"insertOnes"},
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"}
    )
    public String insertOne2(@RequestParam String msg,@RequestParam String id) throws Exception {
        Date start = new Date();
        boolean result = informationService.insertOnes(msg+id,start);
        if (result){
            return "1";
        }else {
            return "0";
        }
    }

    //插入多条数据
    public void insertAll(List<Information> informations){
        String table = "information"+informations.get(0).getEquipmentid();
        int result = informationService.insertAll(informations,table);
    }

    //根据设备编号，某个时间段，删除数据
    @ResponseBody
    @RequestMapping(value = "deleteAll",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
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
    @RequestMapping(value = "selectByIdToExcal",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
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
    @RequestMapping(value = "createTable",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public boolean createTable(@RequestBody String id){

        boolean result = informationService.createTable("information"+id);
        return true;
    }
    @ResponseBody
    @RequestMapping(value = "insertTest",method=RequestMethod.POST,produces = "application/text;charset=UTF-8")
    public boolean insertTest(@RequestBody String msg){
        System.out.print(msg);
        return true;
    }
    @ResponseBody
    @RequestMapping(value = "selectAll",method = RequestMethod.POST,produces = "application/json;chaset=UTF-8")
    public JSONArray selectAll(){
        JSONArray jsonArray = new JSONArray();
        List<Information> informations = informationService.selectAll();

        JSONObject jsonObject = new JSONObject();
        for (int i = 0;i<informations.size();i++){
            jsonObject.put("id",informations.get(i).getId());
            jsonObject.put("datecreatetime",informations.get(i).getDatecreatetime());
            jsonObject.put("equipment",informations.get(i).getEquipmentid());
            jsonObject.put("pressure",informations.get(i).getPressure());
            jsonObject.put("inclination",informations.get(i).getInclination());
            jsonObject.put("state",informations.get(i).getState());
            jsonObject.put("floemeter",informations.get(i).getFlowmeter());
            jsonObject.put("temperature",informations.get(i).getTemperature());
            jsonObject.put("battery",informations.get(i).getBattery());
            jsonObject.put("contain1",informations.get(i).getContain1());
            jsonObject.put("contain2",informations.get(i).getContain2());
            jsonObject.put("contain3",informations.get(i).getContain3());
            jsonObject.put("devicename",informations.get(i).getEquipment().getDevicename());
            jsonObject.put("version",informations.get(i).getEquipment().getVersion());
            jsonObject.put("devicetype",informations.get(i).getEquipment().getDevicetype());
            jsonObject.put("nbiot_Gprs",informations.get(i).getEquipment().getNbiot_Gprs());
            jsonObject.put("positions",informations.get(i).getEquipment().getPositions());
            jsonObject.put("state",informations.get(i).getEquipment().getState());
            jsonObject.put("serviceman1",informations.get(i).getEquipment().getServiceman1());
            jsonObject.put("serviceman2",informations.get(i).getEquipment().getServiceman2());
            jsonObject.put("address",informations.get(i).getEquipment().getAddress());
            jsonObject.put("comment",informations.get(i).getEquipment().getComment());
            jsonArray.add(jsonObject);
            System.out.println(jsonObject);
        }
        System.out.println(jsonArray);
        return jsonArray;
    }
    @Scheduled(cron = "0 10 0 1 * ?")
    public void download(){
        List<Information> informations = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        //过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        informationService.selectByTime(m);
        informationService.deleteToNow(m);
    }
    @Test
    public void test() throws Exception {
        String msg = "msg=0000005+02+03+0004+05&id=0002020613";
        System.out.println("-----------");
        String s = insertOne(msg);
        System.out.println(s);
    }
}