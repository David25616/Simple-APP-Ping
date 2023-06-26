package com.byborgip.excercises.config;

import com.byborgip.excercises.commands.Command;
import com.byborgip.excercises.commands.ICMPPingCommand;
import com.byborgip.excercises.commands.TCPPingCommand;
import com.byborgip.excercises.commands.TraceCommand;
import com.byborgip.excercises.model.Host;
import com.byborgip.excercises.services.HostService;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class ScheduleConfiguration {

    private final HostService service;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

    private ScheduledFuture<?> futureIcmpPingTask, futureTcpPingTask, futureTracePingTask;

    private Runnable runIcmpTask = () -> runCommand(ICMPPingCommand.getInstance());

    private Runnable runTcpTask = () -> runCommand(TCPPingCommand.getInstance());

    private Runnable runTraceTask = () -> runCommand(TraceCommand.getInstance());


    private void runCommand(Command command){
        service.getAll().stream().forEach(command::run);
    }

    public ScheduleConfiguration(){
        this.service = HostService.getInstance();
    }

    public void notifyIcmpPingDelayUpdated(DelayConfig config){

        if(futureIcmpPingTask != null)
                this.futureIcmpPingTask.cancel(false);

        if(config== null)
            return;

        futureIcmpPingTask = scheduler.scheduleAtFixedRate(runIcmpTask, 0, config.getDelay(), config.getUnit());

    }

    public void notifyTcpPingDelayUpdated(DelayConfig config){

        if(futureTcpPingTask != null)
            this.futureTcpPingTask.cancel(false);

        if(config== null)
            return;

        futureTcpPingTask = scheduler.scheduleAtFixedRate(runTcpTask, 0, config.getDelay(), config.getUnit());

    }

    public void notifyTracePingDelayUpdated(DelayConfig config){

        if(futureTracePingTask != null)
            this.futureTracePingTask.cancel(false);

        if(config== null)
            return;

        futureTracePingTask = scheduler.scheduleAtFixedRate(runTraceTask, 0, config.getDelay(), config.getUnit());

    }
}
