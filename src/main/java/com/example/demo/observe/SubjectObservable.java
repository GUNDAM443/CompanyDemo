package com.example.demo.observe;

/**
 * @author : pp
 * @date : Created in 2020/6/9 16:59
 */
public interface SubjectObservable {
    //添加观察者
    void addObserver(MyObserver myObserver);
    //去除观察者
    void deleteObserve(MyObserver myObserver);
    //通知观察者
    void notifyAllObserver();
}
