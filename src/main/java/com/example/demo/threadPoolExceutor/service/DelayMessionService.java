package com.example.demo.threadPoolExceutor.service;

import com.example.demo.threadPoolExceutor.bean.CommandSend;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @author : pp
 * @date : Created in 2020/7/7 11:23
 */
@Service
public class DelayMessionService {

    public String send(CommandSend send){
        ScheduledExecutorService scheduled = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> future = scheduled.schedule(() -> {
            try {
                System.out.println("开始执行任务");
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("执行完毕");
            return "我好了";
        }, 1000, TimeUnit.MILLISECONDS);
        System.out.println("阻塞开始");

//        if (scheduled != null){
//            scheduled.shutdownNow();
//            System.out.println("关闭任务");
//        }
        try {
            System.out.println(future.get() + "");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("阻塞结束");

        return null;
    }
}
