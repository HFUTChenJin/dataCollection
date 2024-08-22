package com.GPRS;


import com.example.Dao.AIMapper;
import com.example.Dao.power_quality_realtimeMapper;
import com.example.Dao.power_realtimeMapper;
import lombok.SneakyThrows;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;



import org.springframework.stereotype.Component;



@Component
public class Server{
    public static void test(power_quality_realtimeMapper pqrMapper, power_realtimeMapper prMapper, AIMapper AIMapper){

        util.init_CMD();

        Executor threadPool = Executors.newFixedThreadPool(2);
        ServerSocket server = null;
        try {
            server = new ServerSocket(11451);
            while (true) {
                System.out.println("listening...");

                Socket client = server.accept();
                System.out.println("connected...：" + client.getRemoteSocketAddress());

                ClientHandler handler = new ClientHandler(client,pqrMapper,prMapper,AIMapper,300000);//pqr,pr
                threadPool.execute(new Runnable(){
                    @SneakyThrows
                    public void run() {
                        handler.Readdata();
                    }

                });
                threadPool.execute(new Runnable(){
                    @SneakyThrows
                    public void run() {
                        handler.Writedata();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
//        System.out.println(util.ammeterID);
//        util.SetammeterID("02");
//        System.out.println(util.ammeterID);

    }

}


