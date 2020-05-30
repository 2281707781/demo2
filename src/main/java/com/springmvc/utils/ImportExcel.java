package com.springmvc.utils;

import com.springmvc.entity.Information;
import com.springmvc.entity.Users;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ypl
 * @date 2020/5/17 - 17:41
 **/
public class ImportExcel {
    public static boolean isXls(String fileName) {
        // (?i)忽略大小写
        if (fileName.matches("^.+\\.(?i)(xls)$")) {
            return true;
        } else if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return false;
        } else {
            throw new RuntimeException("格式不对");
        }
    }

    public static List readExcel(String fileName, InputStream inputStream) throws Exception {

        boolean ret = isXls(fileName);

        try {
            // jxl提供的Workbook类
            jxl.Workbook wb = jxl.Workbook.getWorkbook(inputStream);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                List<List> outerList = new ArrayList<List>();
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 0; i < sheet.getRows(); i++) {
                    List innerList = new ArrayList();
                    // sheet.getColumns()返回该页的总列数
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
                        if (cellinfo.isEmpty()) {
                            continue;
                        }
                        innerList.add(cellinfo);
                    }
                    outerList.add(i, innerList);
                    System.out.println();
                }
                return outerList;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 导出(导出到磁盘)
     */

    public static boolean exportExcel(List<Information> information) {
        //第一步，创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet("数据表");
        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row = sheet.createRow(0);
        //第四步，创建单元格，设置表头
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("编号"); //id
        cell = row.createCell(1);
        cell.setCellValue("数据产生时间"); //datecreatetime
        cell = row.createCell(2);
        cell.setCellValue("设备编号"); //equipmentid
        cell = row.createCell(3);
        cell.setCellValue("水压"); //pressure
        cell = row.createCell(4);
        cell.setCellValue("倾斜角度"); //inclination
        cell = row.createCell(5);
        cell.setCellValue("阀开状态"); //state
        cell = row.createCell(6);
        cell.setCellValue("水流量"); //flowmeter
        cell = row.createCell(7);
        cell.setCellValue("水温"); //temperature
        cell = row.createCell(8);
        cell.setCellValue("电量"); //battery

        for (int i = 0; i < information.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            Information information1 = information.get(i);
            //创建单元格设值
            row1.createCell(0).setCellValue(information1.getId());
            row1.createCell(1).setCellValue(information1.getDatecreatetime());
            row1.createCell(2).setCellValue(information1.getEquipmentid());
            row1.createCell(3).setCellValue(information1.getPressure());
            row1.createCell(4).setCellValue(information1.getInclination());
            row1.createCell(5).setCellValue(information1.getState());
            row1.createCell(6).setCellValue(information1.getFlowmeter());
            row1.createCell(7).setCellValue(information1.getTemperature());
            row1.createCell(8).setCellValue(information1.getBattery());
        }

        //将文件保存到指定的位置
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
            String fileName = df.format(new Date())+".xls";
            File testFile = new File("D:" + File.separator + "filepath" + File.separator + "test" + File.separator + fileName);
            if (!testFile.exists()) {
                testFile.createNewFile();
            }

            System.out.println("testFile:"+testFile);

            FileOutputStream fos = new FileOutputStream("C:" + File.separator + "filepath" + File.separator + "date" + File.separator + fileName);
            workbook.write(fos);
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Test
    public void downloadExcel(HttpServletResponse response) throws IOException{

        String path = "C:\\Users\\hp\\Desktop\\fire\\1.jpg";
        //1、设置响应的头文件，会自动识别文件内容
        response.setContentType("multipart/form-data");

        //2、设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename=test.xls");

        OutputStream out = null;
        InputStream in = null;
        try {
            //3、输出流
            out = response.getOutputStream();

            //4、获取服务端生成的excel文件，这里的path等于4.8中的path
            in = new FileInputStream(new File(path));

            //5、输出文件
            int b;
            while((b=in.read())!=-1){
                out.write(b);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        finally{
            in.close();
            out.close();

        }
    }

}

