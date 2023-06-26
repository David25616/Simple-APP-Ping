package com.byborgip.excercises.gui;

import com.byborgip.excercises.app.ApplicationContext;
import com.byborgip.excercises.model.Host;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ResultPane extends VBox{

    private PromptCommandResultPane icmpPingResultPane = new PromptCommandResultPane();
    private PromptCommandResultPane traceResultPane = new PromptCommandResultPane();
    private TCPPingCommandResultPane tcpPingCommandResultPane = new TCPPingCommandResultPane();
    private TitledPane icmpPingResultTitlePane = new TitledPane(ApplicationContext.bundle.getString("application.gui.host.result.icmp.ping"), icmpPingResultPane);
    private TitledPane tcpPingResultTitlePane = new TitledPane(ApplicationContext.bundle.getString("application.gui.host.result.tcp.ping"), tcpPingCommandResultPane);
    private TitledPane traceResultTitlePane = new TitledPane(ApplicationContext.bundle.getString("application.gui.host.result.trace"), traceResultPane);

    public ResultPane(){
        super(5);
        this.getChildren().addAll(icmpPingResultTitlePane,tcpPingResultTitlePane,traceResultTitlePane);
        this.icmpPingResultTitlePane.getStyleClass().add("titled-pane-host");
        this.tcpPingResultTitlePane.getStyleClass().add("titled-pane-host");
        this.traceResultTitlePane.getStyleClass().add("titled-pane-host");
    }

    public void updateResult(Host.HostCommandResult results){
        if(results==null){
            this.clear();
            return;
        }
        this.icmpPingResultPane.update(results.getLastIcmpPingResult());
        this.traceResultPane.update(results.getLastTraceCommandResult());
        this.tcpPingCommandResultPane.update(results.getLastTcpPingResult());
    }

    public void clear(){
        this.icmpPingResultPane.clear();
        this.traceResultPane.clear();
        this.tcpPingCommandResultPane.clear();
    }

}
