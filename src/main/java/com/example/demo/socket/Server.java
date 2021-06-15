package com.example.demo.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : pp
 * @date : Created in 2020/11/30 14:04
 */
public class Server {
    public static void main(String[] args) throws IOException {
        //1、创建服务器，指定端口ServerSocket(int port)
        ServerSocket socket = new ServerSocket(8880);

        //2、接收 客户端连接
        Socket client = socket.accept();
        System.out.println("******************");
        //获取数据的输入流
        InputStream is = client.getInputStream();
        //使用字符缓存流
        BufferedReader br = new BufferedReader(new
                InputStreamReader(is));
        String msg = "";
        while ((msg = br.readLine()) != null) {
            System.out.println(msg);
        }
        br.close();
    }
}
