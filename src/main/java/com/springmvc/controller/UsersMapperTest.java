package com.springmvc.controller;

import com.springmvc.entity.Users;
import com.springmvc.service.UserService;
import com.springmvc.utils.ImportExcel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ypl
 * @date 2020/5/16 - 0:00
 **/
@RequestMapping("/admin/")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext.xml")
@Controller
public class UsersMapperTest {
    @Resource
    private UserService userService;

    @Test
    public void main(){
        List<Users> user = userService.select();
        System.out.println(user.toString());
    }
    //用户上传
    @RequestMapping("upload")
    public String upload(){
        return "admin/user";
    }

    @RequestMapping("uploads")
    public String uploads(@RequestParam MultipartFile fileUpload) {
        try {
             String fileName = fileUpload.getOriginalFilename();
             // 获取上传文件的输入流
             InputStream inputStream = fileUpload.getInputStream();
             // 调用工具类中方法，读取excel文件中数据
             List sourceList = ImportExcel.readExcel(fileName, inputStream);
             //将excal数据存入到users对象数组
             List<Users> users = new ArrayList<Users>();
             Users user1 = new Users();
            for (int i = 0;i < sourceList.size();i++){
                List var = (List) sourceList.get(i);
                user1.setUsername(var.get(0).toString());
                user1.setPassword(var.get(1).toString());
                user1.setPhone(var.get(2).toString());
                user1.setUsertype(var.get(3).toString());
                user1.setComment(var.get(4).toString());
                users.add(user1);
            }
            userService.insertAll(users);

        } catch (Exception e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
        }
        return "admin/user";
    }
    @Test
    public void test(){
        String table = "test";
        userService.createTable(table);
    }
    @Scheduled(cron = "0 10 0 1 * ?")
    public  void myTest(){
        System.out.println("数据已被处理");
    }
}