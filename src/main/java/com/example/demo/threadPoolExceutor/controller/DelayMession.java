package com.example.demo.threadPoolExceutor.controller;

import com.example.demo.threadPoolExceutor.bean.CommandSend;
import com.example.demo.threadPoolExceutor.dao.DelayMessionDao;
import com.example.demo.threadPoolExceutor.service.DelayMessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : pp
 * @date : Created in 2020/7/7 10:51
 */
@Controller
@Slf4j
public class DelayMession {

    @Autowired
    private DelayMessionDao delayMessionDao;
    @Autowired
    private DelayMessionService delayMessionService;

    @PostMapping("/add")
    public String addTask(CommandSend commandSend){
        log.info("接受到消息 {}",commandSend);

        delayMessionDao.upsert(commandSend);

        String time = commandSend.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String send=null;
        try {
            Date parse = simpleDateFormat.parse(time);
            long future = parse.getTime();
            long preSendTime=future-System.currentTimeMillis();
            send = delayMessionService.send(commandSend);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return send;
    }


}
