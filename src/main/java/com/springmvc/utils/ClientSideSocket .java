package com.springmvc.utils;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author ypl
 * @date 2020/5/16 - 14:44
 * 处理维护大量socket（即终端）,减轻后端工作
 * 目前交互全部使用socket，采用字符串命令控制
 **/
class ClientSideSocket {
    public static void main(String[] args) {
        try {
            final Socket socket = new Socket("47.95.214.173", 8096 );
            final Scanner scanner = new Scanner(System.in);

            System.out.println("I am a client!");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(true){
                            String str = scanner.nextLine();
                            socket.getOutputStream().write(str.getBytes());
                            socket.getOutputStream().flush();
                        }
                    } catch (IOException e) {
                        System.out.println("Writing Quit.");
                        System.exit(0);
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        byte[] bytes = new byte[1024];
                        int n = 0;
                        while(true){
                            while ( (n = socket.getInputStream().read(bytes)) != -1){
                                String str = new String (bytes, 0, n);
                                System.out.println(str);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Reading Quit.");
                        System.exit(0);
                    }
                }
            }).start();



        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}