package com.example.demo.lambda;


import java.util.ArrayList;

/**
 * @author : pp
 * @date : Created in 2020/11/30 16:04
 */
public class Lambda {
    public static volatile int a = 0;

    public static void increase() {
        a++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        //Thread[] threads = new Thread[20];
        for (int i = 0; i < THREADS_COUNT; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
            }).start();
        }
//        while (Thread.activeCount() > 1) {
//            Thread.currentThread().getThreadGroup().list();
//            Thread.yield();
//        }
        System.out.println(a);
    }
}
