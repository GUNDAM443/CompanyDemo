package com.example.demo;

import com.example.demo.schedule.TestJob;
import javafx.application.Application;

import org.junit.Test;
import org.redisson.api.RBitSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : pp
 * @date : Created in 2021/6/16 13:52
 */

public class RedissonTest {
//    @Autowired
//    private RedissonClient redissonClient;
//
//    @Test
//    public void test01(){
//        RBitSet bitSet = redissonClient.getBitSet("bitSet");
//        String name = bitSet.getName();
//        System.out.println(name);
//    }

    @Autowired
    private TestJob testJob;
    @Test
    public void test02(){
        System.out.println("1111");
//        testJob.runfirst();
    }
}
