package com.byborgip.excercises.app;

import com.byborgip.excercises.config.Configuration;
import com.byborgip.excercises.model.Host;
import com.byborgip.excercises.services.HostService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    public static ResourceBundle bundle = ResourceBundle.getBundle("application");
    private final Configuration config;
    private final Map<Long, Host> hosts;

    public ApplicationContext(){
        hosts = new ConcurrentHashMap<>();
        config = new Configuration();
    }

    public Map<Long, Host> getHosts(){
        return hosts;
    }

    public Configuration getConfig() {
        return config;
    }

}
