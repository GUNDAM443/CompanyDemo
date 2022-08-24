package com.example.demo.hello;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.schedule.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author : pp
 * @date : Created in 2021/1/6 11:18
 */
@Slf4j
@RestController
public class HelloController {
    public static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable(value = "name") String name) {
        logger.info("请求进来了" + name);
        logger.error("出去了");
//        int i = 1/0;
        logger.debug("滚蛋了");
        return "i get message " + name;
    }

    @PostMapping("/rec")
    public ApiV2PagePropertyDataVO rec(@RequestBody  ApiV2PagePropertyDataVO o){
        log.info("i get it :{}", o.toString());
        return o;
    }
}
