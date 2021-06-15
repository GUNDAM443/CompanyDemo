package com.example.demo.socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author : pp
 * @date : Created in 2020/11/30 14:05
 */
public class Client {
    public static void main(String[] args) throws UnknownHostException,
            IOException {
        //创建客户端 必须指定服务器+端口
        Socket client = new Socket("localhost", 8880);
        //发送消息 请求资源

        //获取发送流
        OutputStream os = client.getOutputStream();
        BufferedWriter br = new BufferedWriter(new
                OutputStreamWriter(os));
        //写出消息，发送内容
        String msg = "hello I need some source";
        br.write(msg);
        br.close();
    }
}
