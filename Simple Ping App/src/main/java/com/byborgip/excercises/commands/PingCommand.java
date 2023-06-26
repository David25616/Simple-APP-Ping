package com.byborgip.excercises.commands;

import com.byborgip.excercises.app.Application;
import com.byborgip.excercises.model.Host;

public abstract class PingCommand extends Command {

    @Override
    protected void doRun(Host host) {
        this.ping(host);
        Application.APP.save();
    }

    protected abstract void ping(Host host);
}
