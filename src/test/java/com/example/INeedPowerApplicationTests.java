package com.example;

import com.example.Dao.AIMapper;
import com.example.Dao.UserMapper;
import com.example.Entity.Ai;
import com.example.Entity.User;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class INeedPowerApplicationTests {

    @Autowired
    private UserMapper UserMapper;// 继承了BaseMapper，所有的方法都来自己父类 // 我们也可以编写自己的扩展方法！
    @Autowired
    private AIMapper AIMapper;

    @Test
    public void testInsert(){
        User user = new User();

        user.setName("test");
        user.setAge(3);
        user.setEmail("***@163.com");
        int result = UserMapper.insert(user); // 帮我们自动生成id
        System.out.println(result); // 受影响的行数
        System.out.println(user); // 发现，id会自动回填
    }

    @Test
    public void aitestinsert(){
        Ai ai = new Ai();
        ai.setTest("test");
//        ai.setId(new Long((long)3));

        System.out.println("AIMapper是空？"+AIMapper==null);

        int result = AIMapper.insert(ai);
        System.out.println(result);
        System.out.println(ai);


    }

    @Test
    void contextLoads() {
        List<User> users = UserMapper.selectList(null);
        users.forEach(System.out::println);
    }








}
