package com.example.demo.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;

/**
 * @author : pp
 * @date : Created in 2020/7/7 16:04
 */
public class QuartzTest {


        public static void main(String[] args) throws SchedulerException {
            //创建调度器
            //将具体的作业类（RamJob）绑定到调度任务详情中
            //创建触发器
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            JobDetail jobDetail = newJob(RamJob.class)
                    .withDescription("this is a job")
                    .withIdentity("job1","group1")
                    .usingJobData("level","老")
                    .usingJobData("传递","传递的定时任务参数")
                    .build();

            JobDataMap jobDataMap = jobDetail.getJobDataMap();
            jobDataMap.put("job","司机");
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withDescription("this is a trigger1")
                    //.withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(3,1))
                .withSchedule(CronScheduleBuilder.cronSchedule("0 42 17 * * ?"))
                    .startAt(new Date(System.currentTimeMillis()+10000))
                    .withIdentity("trigger1","group1")
                    .build();

            //将触发器以及调度任务详情绑定到调度器上
            Date date = scheduler.scheduleJob(jobDetail, trigger);
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
            //启动调度器
            scheduler.start();
        }



}
