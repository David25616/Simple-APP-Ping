package com.byborgip.excercises.services;

import com.byborgip.excercises.app.Application;
import com.byborgip.excercises.model.Host;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class HostService {

    private static HostService instance;
    private static AtomicLong counter;


    public static synchronized HostService getInstance(){
        return instance!=null ? instance : (instance = new HostService());
    }

    public static void init(int size) {
        counter = new AtomicLong(size);
    }

    public Host addHost(Host host){
        host.setId(counter.incrementAndGet());
        Application.APP.getContext().getHosts().put(host.getId(),host);
        Application.saveApp();
        return host;
    }

    public Host getHostById(long id){
        return Application.APP.getContext().getHosts().get(id);
    }

    public List<Host> getAll(){
        return Application.APP != null && Application.APP.getContext()!=null ?
                new ArrayList<>(Application.APP.getContext().getHosts().values()) :
                Collections.emptyList();
    }

    public void deleteHost(Host host){

        Application.APP.getContext().getHosts().remove(host.getId());
        Application.saveApp();

    }
}
