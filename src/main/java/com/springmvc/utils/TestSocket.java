package com.springmvc.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ypl
 * @date 2020/5/30 - 21:11
 **/
public class TestSocket {
    public static void main(String[] args)throws IOException{
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress("47.95.214.173", Integer.parseInt("8096")));
        while (true){
            Socket s = ss.accept();
        }
    }
}