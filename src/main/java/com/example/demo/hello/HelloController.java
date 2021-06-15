package com.example.demo.hello;

import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : pp
 * @date : Created in 2021/1/6 11:18
 */
@RestController
public class HelloController {
    public static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable(value = "name") String name) {
        logger.info("请求进来了" + name);
        logger.error("出去了");
        int i = 1/0;
        logger.debug("滚蛋了");
        return "i get message " + name;
    }
}
