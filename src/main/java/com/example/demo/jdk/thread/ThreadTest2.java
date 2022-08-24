package com.example.demo.jdk.thread;

/**
 * @author : pp
 * @date : Created in 2022/4/7 18:04
 */
public class ThreadTest2 {
    public static int n = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for(int i = 0;i < 1000;i++){
                n++;
            }
        });

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(n);
    }
}
