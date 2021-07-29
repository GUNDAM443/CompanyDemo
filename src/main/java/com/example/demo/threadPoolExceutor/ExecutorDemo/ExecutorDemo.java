package com.example.demo.threadPoolExceutor.ExecutorDemo;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : pp
 * @date : Created in 2021/6/16 15:51
 */

public class ExecutorDemo {
    /**
     * newFixedThreadPool(int Threads):创建固定数目的线程池。
     * newSingleThreadPoolExecutor():创建一个单线程化的Executor
     * newCacheThreadPool():创建一个可缓存的线程池，调用execute将重用以前构成的线程（如果线程可用）。
     * 如果没有可用的线程，则创建一个新线程并添加到池中。终止并从缓存中移出那些已有60秒钟未被使用的线程。
     * newScheduledThreadPool(int corePoolSize)创建一个支持定时及周期性的任务执行的线程池，
     * 多数情况下可用来替代Time类。
     *
     * 阿里巴巴java开发手册中明确指出，不允许使用Executors创建线程池。
     *
     */
    public static ExecutorService executors = Executors.newFixedThreadPool(15);



    public static void main(String[] args) {

//        executors.submit(()->{
//            for (int i=0;i<Integer.MAX_VALUE;i++){
//                System.out.println(i);
//            }
//        });
//
        String a = "aklhdjfkajdklfj a;sdkljfalsdj;flk djsf;lkjsd&*()a;lfkjad;slkfjasdklfja;ldksfja;ldksjalksj;aklsj;kdsajflks;flk;adsjf;kasdjfalkdjsfl;kadjsflkajsdfkja;sdkfl";
        int i = a.hashCode();
        System.out.println(i);
        String b = "aaklhdjfkajdklfja;sdkljfalsdj;flkdjsf;lkjsda;lfkj ad;slkfjasdklfja;ldksfja;ldksjalksj;aklsj;kdsajflks;flk;adsjf;kasdjfalkdjsfl;kadjsflkajsdadskjf;lkjasd;lkfja;lkdsjf;ljsda;flkjas;dkfja;dlkfja;sdkjf;aksdj;faksdj;flkasjd;f;sklfadkj;faklds123123123[][][][]{}fkja;sdkfl";
        int k = b.hashCode();
        System.out.println(k);
        Object[] o = new Object[2];
        o[1] = "adf";
        Object o1 = JSON.toJSON(o);
        String s = Arrays.toString(o);
        System.out.println(o1);

    }
}
