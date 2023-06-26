package com.byborgip.excercises.utils;

public interface Observer<T> {
    void notifyChanges(T obj);
}
