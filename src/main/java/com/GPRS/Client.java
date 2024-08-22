package com.GPRS;

import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;




/*
* 用来向server发送数据进行功能测试
* */
public class Client {

    public static void main(String[] args) {

        Socket server = null;
        try {
            System.out.println("connecting...");
//            server = new Socket("127.0.0.1", 11451);
            server = new Socket("115.236.153.172", 41937);
            System.out.println("connection success");


            // 输入任意字符发送，输入q退出
            Scanner in = new Scanner(System.in);
            String str = "01 10 00 00 00 02 04 00 01 00 00 a2 6f"; //发送的16进制字符串
            byte[] bytes = util.hexStringToByteArray(str);
            OutputStream os = server.getOutputStream();

            while (!(in.nextLine()).equals("q")) { //输入q退出
                os.write(bytes);
                LocalDateTime dateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                System.out.println(dateTime.format(formatter)+": "+bytes);
            }
            System.out.println("向服务器写入完成");


            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (Exception e) {

                }
            }
        }
    }
}
