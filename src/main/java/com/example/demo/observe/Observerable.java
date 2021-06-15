package com.example.demo.observe;

/**
 * @author : pp
 * @date : Created in 2020/6/9 17:12
 */
public class Observerable implements MyObserver {
    @Override
    public void update() {
        System.out.println("通知到我了");
    }
}
