package com.example.demo.threadPoolExceutor;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author : pp
 * @date : Created in 2020/7/7 9:23
 */

public class DelayThreadMission {

    public static void main(String[] args) {

//        ScheduledExecutorService scheduled = Executors.newSingleThreadScheduledExecutor();
//        ScheduledFuture<?> future = scheduled.schedule(() -> {
//            try {
//                System.out.println("开始执行任务");
//                TimeUnit.SECONDS.sleep(3);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            System.out.println("执行完毕");
//        }, 1000, TimeUnit.MILLISECONDS);
//        System.out.println("阻塞开始");
//
//        if (scheduled != null){
//            scheduled.shutdownNow();
//            System.out.println("关闭任务");
//        }
//        try {
//            System.out.println(future.get() + "");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        System.out.println("阻塞结束");


        /**
         * 启动一个带返回值的延时任务
         */
        ScheduledThreadPoolExecutor scheduled1 = new ScheduledThreadPoolExecutor(1);

        ScheduledFuture<String> future1 = scheduled1.schedule(() -> {
            try {
                System.out.println("开始执行任务");
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("执行完毕");
            return "success";
        }, 1000, TimeUnit.MILLISECONDS);
        System.out.println("阻塞开始");
        try {
            System.out.println(future1.get() + "");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("阻塞结束");






    }


}
