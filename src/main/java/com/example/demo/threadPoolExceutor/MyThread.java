package com.example.demo.threadPoolExceutor;

import cn.hutool.core.date.DateUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : pp
 * @date : Created in 2020/5/7 14:35
 */
@Slf4j
public class MyThread {



    public static void main(String[] args) {
        AtomicInteger count=new AtomicInteger(0);

        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(10,20,30, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000),
                r -> new Thread(r,"我是线程"+count.getAndIncrement()),
                (r,executor)->{
                    log.error("session id {} schedulePool {} down to handle text cmd data over queue limit size", count.get(), "textCmdThreadPool");
                });



        for (int i = 1; i <= 5000000; i++) {
            MyTask task = new MyTask(String.valueOf(i));

            threadPoolExecutor.execute(task);

            Future<?> future = threadPoolExecutor.submit(task);
            if (future.isCancelled() && future.isDone()){
                System.out.println("ok了");
            }
        }



    }

    static class MyTask implements Runnable {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
//            try {
                System.out.println(this.toString() + " is running!");
                //Thread.sleep(3000); //让任务执行慢点
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MyTask [name=" + name + "]";
        }
    }
}
