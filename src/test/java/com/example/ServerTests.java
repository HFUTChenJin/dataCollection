package com.example;


import com.GPRS.Server;
import com.example.Dao.power_quality_realtimeMapper;
import com.example.Dao.power_realtimeMapper;
import com.example.Dao.AIMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerTests {
    @Autowired
    private AIMapper AIMapper;
    @Autowired
    private power_quality_realtimeMapper pqrMapper;
    @Autowired
    private power_realtimeMapper prMapper;

    @Test
    void test1() {
        Server.test(pqrMapper,prMapper,AIMapper);
    }

}