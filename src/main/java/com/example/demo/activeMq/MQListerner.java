package com.example.demo.activeMq;

import sun.applet.Main;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author : pp
 * @date : Created in 2020/8/18 17:06
 * 生产者监听器
 */
public class MQListerner implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
