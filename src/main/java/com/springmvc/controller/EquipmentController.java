package com.springmvc.controller;

import com.springmvc.entity.Equipment;
import com.springmvc.entity.Users;
import com.springmvc.service.EquipmentService;
import com.springmvc.utils.ImportExcel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ypl
 * @date 2020/5/19 - 21:52
 **/
@RequestMapping("/equipment/")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext.xml")
@Controller
public class EquipmentController {
    /**
     * 系统请求路径 http://localhost/firedrants/equipment/*
     * @return 返回Json数据
     * 请求路径http://localhost/firedrants/equipment/*
     *
     */

    @Resource
    private EquipmentService equipmentService;
    //查询设备信息，一条
    @ResponseBody
    @RequestMapping(value = "selectOneById",method= RequestMethod.POST,produces = "appllcation/json;charset=UTF-8")
    public JSONObject selectOneByid(@RequestBody JSONObject param){
        JSONObject jsonObject = JSONObject.fromObject(param);
        //赋值，查询
        String id = jsonObject.getString("id");
        Equipment equipment = equipmentService.selectOneById(id);
        jsonObject.put("devicename",equipment.getDevicename());
        jsonObject.put("version",equipment.getVersion());
        jsonObject.put("devicetype",equipment.getDevicetype());
        jsonObject.put("nbiot_Gprs",equipment.getNbiot_Gprs());
        jsonObject.put("positions",equipment.getPositions());
        jsonObject.put("state",equipment.getState());
        jsonObject.put("serviceman1",equipment.getServiceman1());
        jsonObject.put("serviceman2",equipment.getServiceman2());
        jsonObject.put("address",equipment.getAddress());
        jsonObject.put("comment",equipment.getComment());
        return jsonObject;
    }

    //查询所有设备
    @ResponseBody
    @RequestMapping(value = "selectAll",method = RequestMethod.POST,produces = "appllcation/json;charset=UTF-8")
    public JSONArray selectAll(){
        JSONArray jsonArray = new JSONArray();
        List<Equipment> equipment = equipmentService.selectAll();
        for(int i = 0;i < equipment.size();i++){
            jsonArray.add(equipment.get(i));
        }
        return jsonArray;
    }

    //添加设备
    @ResponseBody
    @RequestMapping(value = "insertOne",method = RequestMethod.POST,produces = "appllcation/json;charset=UTF-8")
    public boolean insertOne(@RequestBody JSONObject param){
        JSONObject jsonObject = JSONObject.fromObject(param);
        //查询该消防栓编号是否存在
        List<Equipment> equipments = equipmentService.selectOneByid(jsonObject.getString("id"));
        //验证是否查询到
        if (equipments.isEmpty()){
            Equipment equipment = new Equipment();
            equipment.setId(jsonObject.getString("id"));
            equipment.setDevicename(jsonObject.getString("devicename"));
            equipment.setDevicetype(jsonObject.getString("devicetype"));
            equipment.setVersion(jsonObject.getString("version"));
            equipment.setAddress(jsonObject.getString("address"));
            equipment.setNbiot_Gprs(jsonObject.getString("nbiot_Gprs"));
            equipment.setPositions(jsonObject.getString("positions"));
            equipment.setState(jsonObject.getInt("state"));
            equipment.setServiceman1(jsonObject.getString("serviceman1"));
            equipment.setServiceman2(jsonObject.getString("serviceman2"));
            equipment.setComment(jsonObject.getString("comment"));
            boolean result =  equipmentService.insertOne(equipment);
            return result;
        } else {
            return false;
        }
    }
    //修改设备信息
    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST,produces = "appllcation/json;charset=UTF-8")
    public boolean update(@RequestBody JSONObject param){
        JSONObject jsonObject = JSONObject.fromObject(param);
        //查询该消防栓编号是否存在
        List<Equipment> equipments = equipmentService.selectOneByid(jsonObject.getString("id"));

        //验证是否查询到
        if (equipments.isEmpty()){
            return false;
        } else {
            Equipment equipment = new Equipment();
            equipment.setId(jsonObject.getString("id"));
            equipment.setDevicename(jsonObject.getString("devicename"));
            equipment.setDevicetype(jsonObject.getString("devicetype"));
            equipment.setVersion(jsonObject.getString("version"));
            equipment.setAddress(jsonObject.getString("address"));
            equipment.setNbiot_Gprs(jsonObject.getString("nbiot_Gprs"));
            equipment.setPositions(jsonObject.getString("positions"));
            equipment.setState(jsonObject.getInt("state"));
            equipment.setServiceman1(jsonObject.getString("serviceman1"));
            equipment.setServiceman2(jsonObject.getString("serviceman2"));
            equipment.setComment(jsonObject.getString("comment"));
            boolean result = equipmentService.updateOne(equipment);
            return result;
        }
    }
    /*@Test
    public void test(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","132");
        jsonObject.put("devicename","#1232");
        jsonObject.put("version","first");
        jsonObject.put("devicetype","11");
        jsonObject.put("nbiot_Gprs","11");
        jsonObject.put("positions","11");
        jsonObject.put("state",1);
        jsonObject.put("serviceman1","张三123456789");
        jsonObject.put("serviceman2","李四123456789");
        jsonObject.put("address","青岛市黄岛区");
        jsonObject.put("comment","测试机器");
        boolean result = update(jsonObject);
        System.out.println(result);
    }*/
    //批量添加设备
    @ResponseBody
    @RequestMapping(value = "insertAll",method = RequestMethod.POST)
    public boolean insertAll(@RequestParam("uploadFile") MultipartFile uploadFile){
        try {
            String fileName = uploadFile.getOriginalFilename();
            // 获取上传文件的输入流
            InputStream inputStream = uploadFile.getInputStream();
            // 调用工具类中方法，读取excel文件中数据
            List sourceList = ImportExcel.readExcel(fileName, inputStream);
            //将excal数据存入到users对象数组
            List<Equipment> equipments = new ArrayList<Equipment>();
            for (int i = 0;i < sourceList.size();i++){
                Equipment equipment = new Equipment();
                List var = (List) sourceList.get(i);
                equipment.setId(var.get(0).toString());
                equipment.setDevicename(var.get(1).toString());
                equipment.setDevicetype(var.get(2).toString());
                equipment.setVersion(var.get(3).toString());
                equipment.setAddress(var.get(4).toString());
                equipment.setNbiot_Gprs(var.get(5).toString());
                equipment.setPositions(var.get(6).toString());
                equipment.setState(Integer.parseInt(var.get(7).toString()));
                equipment.setServiceman1(var.get(8).toString());
                equipment.setServiceman2(var.get(9).toString());
                equipment.setComment(var.get(10).toString());
                if(selectOne(var.get(0).toString()).isEmpty()){
                   equipments.add(equipment);
                }
            }
            boolean result = equipmentService.insertAll(equipments);
            return result;
        } catch (Exception e){
            return false;
        }
    }
    //用于检测设备是否存在
    public List<Equipment> selectOne(String id){
        return  equipmentService.selectOneByid(id);
    }
    //根据编号删除某一条设备
    @ResponseBody
    @RequestMapping(value = "deleteOne",method = RequestMethod.POST,produces = "appllcation/json;charset=UTF-8")
    public boolean deleteOne(@RequestBody JSONObject param){
        JSONObject jsonObject = JSONObject.fromObject(param);
        boolean result = equipmentService.deleteOne(jsonObject.get("id"));
        return result;
    }
}