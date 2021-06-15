package com.example.demo.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : pp
 * @date : Created in 2020/5/7 13:52
 */
//@Component
public class TestJob {

    @Scheduled(cron = "0/10 * * * * ?")
    public void runfirst() {
        System.out.println("********first job is ok******");
    }

    @Scheduled(fixedRate = 1000 * 10)
    public void runsecend() {
        System.out.println("********second job is ok******");
    }

    @Scheduled(fixedDelay = 1000)
    public void runThird() {
        System.out.println("********third job is ok*******");
    }

    public static void main(String[] args) {
//        Map<String, Object> stringObjectHashMap = new HashMap<>();
//        stringObjectHashMap.put("xiaoming", 1);
//        stringObjectHashMap.put("xiaohonh", 3);
//
//        for (Map.Entry<String, Object> map : stringObjectHashMap.entrySet()) {
//            String key = map.getKey();
//            Object value = map.getValue();
//            System.out.println(key + value);
//        }
//
        System.out.println(Long.parseLong("E00F0102", 16));
    }
}
