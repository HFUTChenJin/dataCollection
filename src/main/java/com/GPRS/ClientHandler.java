package com.GPRS;
import com.example.Dao.AIMapper;
import com.example.Dao.power_quality_realtimeMapper;
import com.example.Dao.power_realtimeMapper;
import com.example.Entity.Ai;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import com.example.Entity.power_quality_realtime;
import com.example.Entity.power_realtime;
import com.sun.javaws.IconUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ClientHandler {

//    @Autowired
    private static AIMapper AIMapper;


    private static power_quality_realtimeMapper pqrMapper;
    private static power_realtimeMapper prMapper;

    private static Socket client;
    private static Boolean WR_FLAG = false;//读写锁,从写命令开始

    private static int sleeptime;
    private static int WRNUM_FLAG;



    public ClientHandler(Socket client,power_quality_realtimeMapper pqrMapper, power_realtimeMapper prMapper,AIMapper AIMapper,int sleeptime) {
        this.client = client;
        this.WR_FLAG = false;
        this.pqrMapper = pqrMapper;
        this.prMapper = prMapper;
        this.sleeptime = sleeptime;
        this.AIMapper = AIMapper;
        WRNUM_FLAG = 0;
    }



    @PostConstruct
    public  static void Readdata(){
        try {
            //封装输入流（接收客户端的流）
            BufferedInputStream bis = new BufferedInputStream(client.getInputStream());
            DataInputStream dis = new DataInputStream(bis);

            Ai Ai;
            power_quality_realtime pqr = new power_quality_realtime();
            power_realtime pr= new power_realtime();



            //接收数据
            byte[] bytes = new byte[1]; // 一次读取一个byte
            String ret = "";
            while (dis.read(bytes) != -1) {
                ret += util.bytesToHexString(bytes) + " ";

                if (dis.available() == 0) { //一个请求
                    if(WR_FLAG == true){
                        LocalDateTime dateTime = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        System.out.println(dateTime.format(formatter)+"--client: "+client.getRemoteSocketAddress() + ": " + ret);

                        //设置读到的数据

                        util.set_Entity(ret,util.cmd_array.get(WRNUM_FLAG),pqr,pr);

 /*                     Ai = new Ai();
                        Ai.setTest(ret);
                        int result = AIMapper.insert(Ai);
*/

                        if(WRNUM_FLAG == util.CMD_NUM-1){
                            System.out.println("写入一次数据库");
                            //计算读不到的数据
                            pqr.setDatatime(util.getCurrentTimestamp());
                            pr.setDatatime(util.getCurrentTimestamp());
                            pqr.setDeviceid(1);
                            pr.setDeviceid(1);
                            //写入数据库
//                            pqrMapper.insert(pqr);
//                            prMapper.insert(pr);
                        }

                        ret = "";
                        WRNUM_FLAG++;
                        WR_FLAG = false ;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("client is over");
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }

    }
    public static void Writedata(){
        try{

            while(true){
                OutputStream os = client.getOutputStream();

                //此处写一个遍历   依次读取n条指令后进程sleep
                //明天再写！今天摸了

//                System.out.println(WRNUM_FLAG);

                while( WR_FLAG == false) {//WRNUM_FLAG<50 &&
                    //发送命令
//                    System.out.println(WRNUM_FLAG+".......");
                    System.out.println();
                    LocalDateTime dateTime = LocalDateTime.now();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


                    if(WRNUM_FLAG<util.CMD_NUM){
                        String str = util.cmd_array.get(WRNUM_FLAG).getCMD();
                        byte[] WriteDTU = util.hexStringToByteArray(str);
                        os.write(WriteDTU);
                        System.out.println(dateTime.format(formatter)+"--server:  "+util.cmd_array.get(WRNUM_FLAG).getName()+": "+str);
    //                        System.out.println(dateTime.format(formatter).getClass().getTypeName());

    //                        System.out.println("write:" + WRNUM_FLAG);

                        WR_FLAG = true;
                    }

                }
                if(WRNUM_FLAG == util.CMD_NUM){
                    WRNUM_FLAG = 0;
                    Thread.sleep(sleeptime);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
