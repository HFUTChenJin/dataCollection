package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




//@MapperScan("com.example.Dao")//扫描Dao文件夹如果使用@Repository


@SpringBootApplication
public class INeedPowerApplication {
    public static void main(String[] args) {
        SpringApplication.run(INeedPowerApplication.class, args);


    }

}
