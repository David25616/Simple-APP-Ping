package com.byborgip.excercises.executors;

public interface IExecutor<T, K> {

    T execute(K param, Runnable execIfError);
}
