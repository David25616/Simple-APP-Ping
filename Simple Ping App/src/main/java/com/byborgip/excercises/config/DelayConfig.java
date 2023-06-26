package com.byborgip.excercises.config;

import java.util.concurrent.TimeUnit;

public class DelayConfig {

    public DelayConfig(){

    }

    private int delay;
    private TimeUnit unit;

    public DelayConfig(int delay, TimeUnit unit){
        this.delay=delay;
        this.unit=unit;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }

    public int getDelay() {
        return delay;
    }

    public TimeUnit getUnit() {
        return unit;
    }
}
