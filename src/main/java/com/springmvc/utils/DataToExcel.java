package com.springmvc.utils;

import com.springmvc.entity.Information;
import com.springmvc.service.UserService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author ypl
 * @date 2020/6/26 - 14:20
 **/
@Repository
public class DataToExcel {
    @Test
    public void test() throws IOException {
        File file = new File("D:/upload/1.xls");
        //获取父目录
        File fileParent = file.getParentFile();
        //判断是否存在
        if (!fileParent.exists()) {
            //创建父目录文件
            fileParent.mkdirs();
        }
        file.createNewFile();
    }
    public  void dataToExcel(String msg, List<Information> list) {
        try {
            WritableWorkbook wwb = null;
            // 创建可写入的Excel工作簿
            String fileName = "C:/upload/"+msg+".xls";
            File file=new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            //以fileName为文件名来创建一个Workbook
            wwb = Workbook.createWorkbook(file);
            // 创建工作表

            WritableSheet ws = wwb.createSheet("设备数据信息", 0);
            //查询数据库中所有的数据
            //要插入到的Excel表格的行号，默认从0开始
            Label labelId= new Label(0, 0, "编号");//表示第
            Label labelTime= new Label(1, 0, "数据接收时间");
            Label labelEquipmentId= new Label(2, 0, "消防栓编号");
            Label labelPress= new Label(3, 0, "水压");
            Label labelInclination= new Label(4, 0, "倾斜角度");
            Label labelState= new Label(5, 0, "阀开状态");
            Label labelflowmeter= new Label(6, 0, "流量");
            Label labelTemperature= new Label(7, 0, "水温");
            Label labelBattery= new Label(8, 0, "电量");
            Label labelContain1= new Label(9, 0, "contain1");
            Label labelContain2= new Label(10, 0, "contain2");
            Label labelContain3= new Label(11, 0, "contain3");
            ws.addCell(labelId);
            ws.addCell(labelTime);
            ws.addCell(labelEquipmentId);
            ws.addCell(labelPress);
            ws.addCell(labelInclination);
            ws.addCell(labelState);
            ws.addCell(labelflowmeter);
            ws.addCell(labelTemperature);
            ws.addCell(labelBattery);
            ws.addCell(labelContain1);
            ws.addCell(labelContain2);
            ws.addCell(labelContain3);
            for (int i = 0; i < list.size(); i++) {
                //Label labelId_i= new Label(0, i+1, list.get(i).getId()+"");
                labelId= new Label(0, i+1, list.get(i).getId()+"");//表示第
                labelTime= new Label(1, i+1, list.get(i).getDatecreatetime()+"");
                labelEquipmentId= new Label(2, i+1, list.get(i).getEquipmentid()+"");
                labelPress= new Label(3, i+1, list.get(i).getPressure()+"");
                labelInclination= new Label(4, i+1, list.get(i).getInclination()+"");
                labelState= new Label(5, i+1, list.get(i).getFlowmeter()+"");
                labelflowmeter= new Label(6, i+1, list.get(i).getFlowmeter()+"");
                labelTemperature= new Label(7, i+1,list.get(i).getTemperature()+"");
                labelBattery= new Label(8, i+1, list.get(i).getBattery()+"");
                labelContain1= new Label(9, i+1, list.get(i).getContain1()+"");
                labelContain2= new Label(10, i+1, list.get(i).getContain2());
                labelContain3= new Label(11, i+1,list.get(i).getContain3());
                ws.addCell(labelId);
                ws.addCell(labelTime);
                ws.addCell(labelEquipmentId);
                ws.addCell(labelPress);
                ws.addCell(labelInclination);
                ws.addCell(labelState);
                ws.addCell(labelflowmeter);
                ws.addCell(labelTemperature);
                ws.addCell(labelBattery);
                ws.addCell(labelContain1);
                ws.addCell(labelContain2);
                ws.addCell(labelContain3);

            }
            //写进文档
            wwb.write();
            // 关闭Excel工作簿对象
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}