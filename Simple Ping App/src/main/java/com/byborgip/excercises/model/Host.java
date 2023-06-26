package com.byborgip.excercises.model;

import com.byborgip.excercises.app.Application;
import com.byborgip.excercises.commands.PromptCommandResult;
import com.byborgip.excercises.commands.TCPPingCommandResult;
import com.byborgip.excercises.utils.Observable;
import com.byborgip.excercises.utils.Observer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

public class Host implements Observable<Host> {

    @JsonIgnore
    public final Set<Observer<Host>> observers = new HashSet<>();
    private long id;

    private String name;

    private int tcpConnectionTimeOut;

    private final HostCommandResult commandResults;

    private String url2Report;

    public Host(){
        this.commandResults = new HostCommandResult();
    }

    public Host(String name, int tcpConnectionTimeOut, String url2Report){
        this.name = name;
        this.commandResults = new HostCommandResult();
        this.tcpConnectionTimeOut = tcpConnectionTimeOut;
        this.url2Report = url2Report;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName(){
        return this.name;
    }

    public int getTcpConnectionTimeOut(){
        return this.tcpConnectionTimeOut;
    }

    public String getUrl2Report(){
        return this.url2Report;
    }

    public void setTcpConnectionTimeOut(int tcpConnectionTimeOut){
        this.tcpConnectionTimeOut = tcpConnectionTimeOut;
    }
    public void setUrl2Report(String url2Report){
        this.url2Report = url2Report;
    }

    public HostCommandResult getCommandResults(){
        return commandResults;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    @Override
    public Set<Observer<Host>> getAllObservers() {
        return observers;
    }

    private void notifyObservers(){
        notifyObservers(this);
    }
    public class HostCommandResult {
        private PromptCommandResult lastIcmpPingResult;
        private TCPPingCommandResult lastTcpPingResult;

        private PromptCommandResult lastTraceCommandResult;

        public synchronized void setLastICMPPingResult(PromptCommandResult lastIcmpPingResult) {
            this.lastIcmpPingResult = lastIcmpPingResult;
            notifyObservers();
        }

        public void setLastTCPPingResult(TCPPingCommandResult lastTcpPingResult) {
            this.lastTcpPingResult = lastTcpPingResult;
            notifyObservers();
        }

        public void setLastTraceCommandResult(PromptCommandResult lastTraceCommandResult) {
            this.lastTraceCommandResult = lastTraceCommandResult;
            notifyObservers();
        }

        public PromptCommandResult getLastIcmpPingResult() {
            return lastIcmpPingResult;
        }

        public TCPPingCommandResult getLastTcpPingResult() {
            return lastTcpPingResult;
        }

        public PromptCommandResult getLastTraceCommandResult() {
            return lastTraceCommandResult;
        }
    }
}
