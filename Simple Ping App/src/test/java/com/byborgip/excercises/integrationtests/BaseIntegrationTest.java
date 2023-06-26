package com.byborgip.excercises.integrationtests;

import com.byborgip.excercises.app.Application;
import com.byborgip.excercises.app.ApplicationContext;
import com.byborgip.excercises.config.DelayConfig;
import com.byborgip.excercises.config.TestConfig;
import com.byborgip.excercises.model.Host;
import com.byborgip.excercises.services.HostService;
import org.junit.Before;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public abstract class BaseIntegrationTest {


    public void setup(){

        ApplicationContext.bundle = ResourceBundle.getBundle(TestConfig.class.getAnnotation(TestResource.class).value());
        Application.APP = new Application(null);

    }

    public void addHost(Host host){
        HostService.getInstance().addHost(host);
    }

    public void deleteHost(Host host){
        HostService.getInstance().deleteHost(host);
    }

    public Host getHostById(long id) {
        return HostService.getInstance().getHostById(id);
    }

    public void scheduleICMPPing(int delay, TimeUnit unit){
        Application.APP.getContext().getConfig().setDelayIcmpPing(new DelayConfig(delay, unit));
    }

    public void scheduleTCPPing(int delay, TimeUnit unit){
        Application.APP.getContext().getConfig().setDelayTcpPing(new DelayConfig(delay, unit));
    }

    public void scheduleTrace(int delay, TimeUnit unit){
        Application.APP.getContext().getConfig().setDelayTrace(new DelayConfig(delay, unit));
    }

}
