package com.example.demo.jdk.time;

import org.springframework.util.CollectionUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : pp
 * @date : Created in 2022/2/24 9:57
 */
public class LocadateTimeTest {
    public static void main(String[] args) {
//        /* 通过静态方法 now() 返回该类的实例 */
//        //获取当前的日期时分秒
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(now);
//
//        //获取当前的日期
//        LocalDate now1 = LocalDate.now();
//        System.out.println(now1);
//
//        //获取当前的时分秒
//        LocalTime now2 = LocalTime.now();
//        System.out.println(now2);
//
//        System.out.println("=========================================");
//        /* 静态方法 of() 返回该类的实例 */
//        //指定日期时分秒
//        LocalDateTime localDateTime = LocalDateTime.of(2048, 11, 25, 12, 00, 30);
//        System.out.println(localDateTime);
//
//        //指定日期
//        LocalDate date = LocalDate.of(2020, 12, 12);
//        System.out.println(date);
//
//        //指定时分秒
//        LocalTime time = LocalTime.of(14, 20, 30);
//        System.out.println(time);
//
//        System.out.println("===================获取相关时间=====================");
//        //获取日期时分秒
////        LocalDateTime now = LocalDateTime.now();
//
//        //获取年份
//        int year = now.getYear();
//        System.out.println(year);
//
//        //获取月份枚举
//        //Month 枚举类，定义了十二个月份
//        Month month = now.getMonth();
//        System.out.println(month);
//
//        //获取星期枚举
//        DayOfWeek dayOfWeek = now.getDayOfWeek();
//        System.out.println(dayOfWeek);
//
//        //获取月份的数值
//        int monthValue = now.getMonthValue();
//        System.out.println(monthValue);
//
//        //获取当天在本月的第几天
//        int dayOfMonth = now.getDayOfMonth();
//        System.out.println(dayOfMonth);
//
//        //获取小时
//        int hour = now.getHour();
//        System.out.println(hour);
//
//        //获取分钟
//        int minute = now.getMinute();
//        System.out.println(minute);
//
//        //获取秒值
//        int second = now.getSecond();
//        System.out.println(second);
//
//        System.out.println("时间格式转化==========================");
//        //获取当前日期时分秒
////        LocalDateTime now = LocalDateTime.now();
//
//        //默认格式  年-月-日T时:分:秒
//        System.out.println(now);
//
//        //指定格式
//        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
//        //传入格式
//        String dateStr = now.format(ofPattern);
//        System.out.println(dateStr);

        Map<String, List<String>> stringListHashMap = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            List<String> list = stringListHashMap.get(i);
            if (list == null){
                String a = "k_" + i;
                List<String> objects = new ArrayList<>();
                objects.add("v_" + i);
                stringListHashMap.put(i + "",objects);
            }else {
                list.add("v_" + i);
            }
        }

        stringListHashMap.forEach((k,v)->{
            System.out.println("key======>" + k);
            System.out.println("value====>" + v);
        });
    }
}
