package com.example.demo.websocket;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : pp
 * @date : Created in 2021/3/24 15:34
 */
@RestController
public class HomeController {
    @GetMapping("/broadcast")
    public void broadcast(){
        MyWebSocket.broadcast();
    }
}
