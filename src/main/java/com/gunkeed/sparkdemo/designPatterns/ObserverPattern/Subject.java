package com.gunkeed.sparkdemo.designPatterns.ObserverPattern;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    private List<Observer> observerList = new ArrayList<>();

    public void addObserver(Observer o){
        observerList.add(o);
    }

    public void delObserver(Observer o){
        observerList.remove(o);
    }

    public void notifyObservers(){
        for (Observer observer : observerList){
            observer.update();
        }
    }
}
