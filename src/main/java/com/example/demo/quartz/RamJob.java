package com.example.demo.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : pp
 * @date : Created in 2020/7/7 16:02
 */
public class RamJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("启动定时任务......每十秒执行一次，共执行三次");
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.println(jobDataMap.get("level") + "" + jobDataMap.get("job"));

    }

    public static void main(String[] args) {
        Date date = new Date(1594285145000L);

        String format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(date);
        System.out.println(format);
    }

}
