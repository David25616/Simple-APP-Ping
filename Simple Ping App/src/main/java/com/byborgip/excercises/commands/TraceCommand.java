package com.byborgip.excercises.commands;

import com.byborgip.excercises.app.Application;
import com.byborgip.excercises.executors.PromptCommandExecutor;
import com.byborgip.excercises.model.Host;

public class TraceCommand extends Command{


    private static final TraceCommand instance = new TraceCommand();

    private static final String COMMAND_FORMAT = "tracert %s";

    @Override
    protected void doRun(Host host) {

        host.getCommandResults()
                .setLastTraceCommandResult(PromptCommandExecutor
                        .getInstance()
                        .execute(String.format(COMMAND_FORMAT, host.getName()), () -> report(host)));


        Application.APP.save();

    }

    public static TraceCommand getInstance() {
        return instance;
    }
}
