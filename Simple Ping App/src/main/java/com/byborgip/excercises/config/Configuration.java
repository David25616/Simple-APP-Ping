package com.byborgip.excercises.config;

import com.byborgip.excercises.app.Application;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Configuration {

    private DelayConfig delayIcmpPing;
    private DelayConfig delayTcpPing;
    private DelayConfig delayTrace;

    @JsonIgnore
    private ScheduleConfiguration scheduleConfiguration;

    public Configuration(){
        this.scheduleConfiguration = new ScheduleConfiguration();
    }
    public void setDelayIcmpPing(DelayConfig delayIcmpPing) {
        this.delayIcmpPing = delayIcmpPing;
        if(this.scheduleConfiguration == null)
            this.scheduleConfiguration = new ScheduleConfiguration();
        this.scheduleConfiguration.notifyIcmpPingDelayUpdated(delayIcmpPing);

        Application.saveApp();
    }

    public void setDelayTcpPing(DelayConfig delayTcpPing) {
        this.delayTcpPing = delayTcpPing;
        if(this.scheduleConfiguration == null)
            this.scheduleConfiguration = new ScheduleConfiguration();
        this.scheduleConfiguration.notifyTcpPingDelayUpdated(delayTcpPing);
        Application.saveApp();
    }

    public void setDelayTrace(DelayConfig delayTrace) {
        this.delayTrace = delayTrace;
        if(this.scheduleConfiguration == null)
            this.scheduleConfiguration = new ScheduleConfiguration();
        this.scheduleConfiguration.notifyTracePingDelayUpdated(delayTrace);
        Application.saveApp();
    }

    public DelayConfig getDelayIcmpPing() {
        return delayIcmpPing;
    }

    public DelayConfig getDelayTcpPing() {
        return delayTcpPing;
    }

    public DelayConfig getDelayTrace() {
        return delayTrace;
    }

}
