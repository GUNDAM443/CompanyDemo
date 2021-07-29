package com.example.demo.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : pp
 * @date : Created in 2020/6/9 17:03
 */
public class FacObservable implements SubjectObservable {
    private List<MyObserver> list = new ArrayList<>();

    @Override
    public void addObserver(MyObserver myObserver) {
        list.add(myObserver);
    }

    @Override
    public void deleteObserve(MyObserver myObserver) {
        list.remove(myObserver);
    }

    @Override
    public void notifyAllObserver() {
        list.forEach(e -> {
            e.update();
        });
    }

    public static void main(String[] args) {
        FacObservable facObservable = new FacObservable();
        facObservable.addObserver(new Observerable());
        facObservable.notifyAllObserver();
    }
}
