package com.springmvc.interceptor;

import com.springmvc.entity.Information;

import java.util.Date;

/**
 * @author ypl
 * @date 2020/6/15 - 13:47
 **/
public class ReadVarchar {
    public static Information readVarchar(String msg){
        Information information = new Information();

        String var = "";
        int j = -1;
        int k = 0;
        for(int i = 0;i < msg.length();i++){
            //System.out.println(msg.charAt(i));
            if(msg.charAt(i) >= '0' && msg.charAt(i) <= '9'){
                var = var + msg.charAt(i);
            }else if(msg.charAt(i) == '+'|| msg.charAt(i) == '='){
                j++;
                k=0;
                if (j!=0){
                    var = var + ",";
                }
            }
        }
        String[] result = var.split(",");
        //压力倾斜角度阀开状态流量电压
        information.setPressure(Integer.valueOf(result[0]));
        information.setInclination(Double.parseDouble(result[1]));
        if (result[2] == "0") {
            information.setState(false);
        } else{
            information.setState(true);
        }
        if (result[2] == "0") {
            information.setFlowmeter(false);
        } else{
            information.setFlowmeter(true);
        }
        information.setDatecreatetime(new Date());
        information.setBattery(Integer.valueOf(result[4]));
        information.setEquipmentid(result[5]);
        return information;
    }
}