package com.springmvc.interceptor;

import com.springmvc.entity.Information;

import java.util.Date;

/**
 * @author ypl
 * @date 2020/6/15 - 13:47
 **/
public class ReadVarchar {
    public static Information varcharToObject(String[] result){
        Information information = new Information();
        System.out.println(result[0]);
        if (testData(result[0])&&result[0].charAt(0)!= ':'){
            information.setPressure(Integer.valueOf(result[0]));
        }else {
            information.setPressure(-1);
        }
        if (testData(result[1])&&result[1].charAt(0)!= ':'){
            information.setInclination(Double.parseDouble(result[1]));
        }else {
            information.setInclination((double) -1);
        }
        if (testData(result[2])){
            if (result[2].charAt(0) == ':'){
                information.setState(100);
            } else {
                information.setState(Integer.valueOf(result[2]));
            }
        }else {
            information.setState(-1);
        }
        if (testData(result[3])&&result[3].charAt(0)!= ':'){
            information.setFlowmeter(Integer.valueOf(result[3]));
        }else {
            information.setFlowmeter(-1);
        }

        information.setDatecreatetime(new Date());
        if (testData(result[4])) {

            System.out.println(result[4]);
            if (result[4].charAt(0) == ':'){

                information.setBattery(100);
            } else {
                information.setBattery(Integer.valueOf(result[4]));
            }

        }else {
            information.setBattery(-1);
        }
        information.setEquipmentid(result[6]);
        return information;
    }
    public static  boolean testData(String msg){
        boolean re = true;
        for (int i = 0;i < msg.length();i++ ){
            if(i == 0 && msg.charAt(i) != ':' &&( msg.charAt(i) < '0'|| msg.charAt(i) > '9')){
                re = false;
            }else if(i != 0 && (msg.charAt(i) < '0'|| msg.charAt(i) > '9')){
                re = false;
            }
        }
        if (msg.charAt(0) == ':' && msg.charAt(1) != '0'){
            re = false;
        }
        return re;
    }
    public static Information readVarchar(String msg) throws Exception{
        Information information = new Information();

        String var = "";
        int j = -1;
        int k = 0;
        for(int i = 3;i < msg.length();i++){
            //System.out.println(msg.charAt(i));
             if(msg.charAt(i) == '+'|| msg.charAt(i) == '=' || msg.charAt(i) == '&'){
                j++;
                k=0;
                if (j!=0){
                    var = var + ",";
                }
            } else {
                var = var + msg.charAt(i);
            }
        }
        System.out.println(var);
        String[] result = var.split(",");
        //压力倾斜角度阀开状态流量电压

        information = varcharToObject(result);
        return information;
    }

    public static boolean adjustVarchar(Information information) {
        boolean result = true;
        if (information.getPressure() < 0 || information.getPressure() > 2000001){
            result = false;
        }
        if (information.getInclination()<0 || information.getInclination()> 90){
            result = false;
        }
        if (information.getState() < 0 || information.getState()> 100){
            result = false;
        }
        if (information.getFlowmeter() < 0 || information.getFlowmeter()> 999){
            result = false;
        }
        if (information.getBattery() < 0 || information.getBattery()> 100){
            result = false;
        }
        return result;
    }
}