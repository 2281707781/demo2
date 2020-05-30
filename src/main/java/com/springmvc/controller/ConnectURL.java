package com.springmvc.controller;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;

/**
 * @author ypl
 * @date 2020/5/25 - 22:27
 **/
@RequestMapping("/ConnectURL/")
@Controller
public class ConnectURL {
    @Test
    public void test(){
        HttpServletRequest request = null;
    }
    //获取在线设备 无参数
    @RequestMapping("getList")
    public void getList(HttpServletRequest request) throws Exception {
        String url = "http://47.95.214.173:8096/getList";
        TestConnect.getData(url);
    }

    /**
     * queryMeter
     * 参数 cmd user msg
     * 查询水表使用，由于不能立刻获取，会暂时返回
     */
    @RequestMapping("queryMeter")
    public void queryMeter(){

    }
    /*
        接受隔段时间返回查询的数据
     */
    @RequestMapping("recieve_from_md")
    public void recieve_from_md(String message){

    }

}