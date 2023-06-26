package com.byborgip.excercises.commands;

import com.byborgip.excercises.executors.PromptCommandExecutor;
import com.byborgip.excercises.model.Host;

public class ICMPPingCommand extends PingCommand{

    private static final ICMPPingCommand instance = new ICMPPingCommand();
    private static final String COMMAND_FORMAT = "ping -n 5 %s";
    private static final int NUM_PACKETS = 5;


    @Override
    protected void ping(Host host) {

        host.getCommandResults().
                setLastICMPPingResult(PromptCommandExecutor.
                        getInstance().
                        execute(String.format(COMMAND_FORMAT, host.getName()), () -> report(host)));

    }

    public static ICMPPingCommand getInstance(){
        return instance;
    }

}
