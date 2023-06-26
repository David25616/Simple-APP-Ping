package com.byborgip.excercises.gui;

import com.byborgip.excercises.app.Application;
import com.byborgip.excercises.app.ApplicationContext;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ConfigPane extends GridPane {
    private Label lbDelayIcmpPing = new Label(ApplicationContext.bundle.getString("application.gui.delay.icmp.ping"));
    private Label lbDelayTcpPing = new Label(ApplicationContext.bundle.getString("application.gui.delay.tcp.ping"));
    private Label lbDelayTrace = new Label(ApplicationContext.bundle.getString("application.gui.delay.trace"));

    private DelayUnitPane dcDelayIcmpPing = new DelayUnitPane();
    private DelayUnitPane dcDelayTcpPing = new DelayUnitPane();
    private DelayUnitPane dcDelayTrace = new DelayUnitPane();

    private Button okBtn = new Button(ApplicationContext.bundle.getString("application.gui.okbtn"));

    public ConfigPane(ConfigDialog parent){
        this.setVgap(10);
        this.setHgap(10);
        this.add(lbDelayIcmpPing,0,0);
        this.add(dcDelayIcmpPing,1,0);

        this.add(lbDelayTcpPing,0,1);
        this.add(dcDelayTcpPing,1,1);

        this.add(lbDelayTrace,0,2);
        this.add(dcDelayTrace,1,2);

        this.add(okBtn,0,3);

        okBtn.getStyleClass().add("button-host");

        okBtn.setOnAction(event -> {
            Application.APP.getContext().getConfig().setDelayIcmpPing(dcDelayIcmpPing.getSelection());
            Application.APP.getContext().getConfig().setDelayTcpPing(dcDelayTcpPing.getSelection());
            Application.APP.getContext().getConfig().setDelayTrace(dcDelayTrace.getSelection());
            parent.closeDialog();
        });
    }

    public void refresh(){
        dcDelayIcmpPing.setSelection(Application.APP.getContext().getConfig().getDelayIcmpPing());
        dcDelayTcpPing.setSelection(Application.APP.getContext().getConfig().getDelayTcpPing());
        dcDelayTrace.setSelection(Application.APP.getContext().getConfig().getDelayTrace());
    }

}
