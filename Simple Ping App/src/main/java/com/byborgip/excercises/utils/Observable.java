package com.byborgip.excercises.utils;

import com.byborgip.excercises.gui.HostPane;

import java.util.List;
import java.util.Set;

public interface Observable<T> {
    default void addObserver(Observer<T> observer){
        this.getAllObservers().add(observer);
    }

    Set<Observer<T>> getAllObservers();
    default void notifyObservers(T obj){
        this.getAllObservers().forEach(observer -> observer.notifyChanges(obj));
    }

    default void removeObserver(Observer<T> observer){
        this.getAllObservers().remove(observer);
    }
}
