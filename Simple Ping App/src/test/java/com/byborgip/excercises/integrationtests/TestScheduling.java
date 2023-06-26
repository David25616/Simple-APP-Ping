package com.byborgip.excercises.integrationtests;

import com.byborgip.excercises.model.Host;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TestScheduling extends BaseIntegrationTest{

    @Before
    public void setup(){
        super.setup();

        Host host1 = new Host("www.google.com", 1000,"www.report.com/err" );
        addHost(host1);

        Host host2 = new Host("www.facebook.com", 2000,"www.report.com/err" );
        addHost(host2);

        Host host3 = new Host("www.youtube.com", 2000,"www.report2.com/err" );
        addHost(host3);
    }

    @Test
    public void testTrace() throws InterruptedException {
        scheduleTrace(1000, TimeUnit.MILLISECONDS);
        Host host = getHostById(1L);
        Assert.assertNull(host.getCommandResults().getLastTraceCommandResult());
        Thread.sleep(40000);
        Assert.assertNotNull(host.getCommandResults().getLastTraceCommandResult());
    }


    @Test
    public void testICMPPing() throws InterruptedException {
        scheduleICMPPing(1, TimeUnit.SECONDS);
        Host host = getHostById(2L);
        Assert.assertNull(host.getCommandResults().getLastIcmpPingResult());
        Thread.sleep(15000);
        Assert.assertNotNull(host.getCommandResults().getLastIcmpPingResult());
    }

    @Test
    public void testTCPPing() throws InterruptedException {
        scheduleTCPPing(1500, TimeUnit.MILLISECONDS);
        Host host = getHostById(3L);
        Assert.assertNull(host.getCommandResults().getLastTcpPingResult());
        Thread.sleep(5000);
        Assert.assertNotNull(host.getCommandResults().getLastTcpPingResult());
    }

}
