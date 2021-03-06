package com.springmvc.controller;

import com.springmvc.entity.Users;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.*;

/**
 * @author ypl
 * @date 2020/5/24 - 22:45
 **/

public class TestConnect {
    @Test
    public static void main(String[] args) throws Exception {
        String url = "http://47.95.214.173:8096/getList";
        String result = getData(url);
        System.out.println(result);
    }

    public static String getData(String urlString) throws Exception {
        String res = "";
        BufferedReader reader = null;

        try {
            URL url = new URL(urlString);
            URLConnection conn = (URLConnection) url.openConnection();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line;
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            reader.close();
        } catch (Exception e) {
            e.getMessage();
        }
        return res;
    }
}