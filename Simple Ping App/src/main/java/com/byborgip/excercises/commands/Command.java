package com.byborgip.excercises.commands;

import com.byborgip.excercises.model.Host;
import com.byborgip.excercises.reports.ReportHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Command {

    public void run(Host host){
        new Thread(() -> doRun(host)).start();
    }

    protected abstract void doRun(Host host);

    protected final void report(Host host){
        ReportHelper.get(host).report();
    }
}
