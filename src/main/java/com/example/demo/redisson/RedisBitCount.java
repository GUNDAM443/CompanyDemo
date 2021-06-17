package com.example.demo.redisson;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : pp
 * @date : Created in 2021/6/16 13:39
 */

public class RedisBitCount {
    @Autowired
    private RedissonClient redissonClient;

    public static void main(String[] args) {
        /**
         * 假设我们要对于0-7内的5个元素(4,7,2,5,3)进行排序（假设元素没有重复），
         * 我们可以利用bitmap达到该目的，要表示8个数，我们需要8bit，每个bit相当于一个标志位，
         * 我们可以通过标志位（0，1）去标识本次这个元素是否存在（0，标识不存在；1，标识存在，1Byte=8bit）
         */
        int[] a = new int[4];
        a[0] = 4;
        a[1] = 2;
        a[2] = 5;
        a[3] = 7;

        for (int i = 0;i<a.length;i++){
            System.out.println(a[i]);
        }


    }
}
