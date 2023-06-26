package com.byborgip.excercises.unittests;

import com.byborgip.excercises.commands.PromptCommandResult;
import com.byborgip.excercises.executors.PromptCommandExecutor;
import org.junit.Assert;
import org.junit.Test;

public class TestPromptCommand {

    @Test
    public void testIcmpPingCommand(){
        PromptCommandResult result = PromptCommandExecutor
                .getInstance()
                .execute("ping -n 5 www.google.com", this::report);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getDateTime());
        Assert.assertNotNull(result.getResult());
    }

    @Test
    public void testTraceCommand(){
        PromptCommandResult result = PromptCommandExecutor
                .getInstance()
                .execute("tracert www.google.com", this::report);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getDateTime());
        Assert.assertNotNull(result.getResult());
    }

    private void report(){
        System.out.println("simulate report");
    }
}
