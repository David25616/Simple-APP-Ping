package com.byborgip.excercises.gui;

import com.byborgip.excercises.app.ApplicationContext;
import com.byborgip.excercises.model.Host;
import com.byborgip.excercises.utils.Observer;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.apache.commons.lang3.StringUtils;

public class HostPane extends VBox implements Observer<Host> {

    private Host host;

    private final ResultPane res;
    private final Label lblId = new Label(ApplicationContext.bundle.getString("application.gui.host.id"));

    private final Label lblName = new Label(ApplicationContext.bundle.getString("application.gui.host.name"));
    private final Label lblTcpConnectionTimeout = new Label(ApplicationContext.bundle.getString("application.gui.tcp.timeout.host"));
    private final Label lblUrl2Report = new Label(ApplicationContext.bundle.getString("application.gui.host.url2report"));
    private final Text tfId = new Text();
    private final Text tfName = new Text();

    private final Text tfTcpConnectionTimeout = new Text();

    private final Text tfUrl2Report = new Text();

    public HostPane(){
        super(10);
        GridPane infos = new GridPane();
        infos.setVgap(2);
        infos.setHgap(40);
        infos.add(lblId, 0, 0);
        infos.add(tfId, 0, 1);
        infos.add(lblName, 1, 0);
        infos.add(tfName, 1, 1);
        infos.add(lblTcpConnectionTimeout, 2, 0);
        infos.add(tfTcpConnectionTimeout, 2, 1);
        infos.add(lblUrl2Report, 3, 0);
        infos.add(tfUrl2Report, 3, 1);
        res = new ResultPane();
        this.getChildren().addAll(infos, res);
        this.clear();
    }
    public void show(Host host){
        if(this.host == host)
            return;

        if(this.host!=null){
            this.host.removeObserver(this);
        }

        if(host != null)
            host.addObserver(this);

        this.host = host;

        refresh();
    }

    public void refresh(){
        if(host == null){
            this.clear();
            this.res.clear();
            return;
        }

        this.setVisible(true);
        this.tfId.setText(String.valueOf(host.getId()));
        this.tfName.setText(host.getName());
        this.tfTcpConnectionTimeout.setText(String.valueOf(host.getTcpConnectionTimeOut()));
        this.tfUrl2Report.setText(host.getUrl2Report());
        this.res.updateResult(host.getCommandResults());
    }

    private void clear() {
        this.tfName.setText(StringUtils.EMPTY);
        this.tfTcpConnectionTimeout.setText(StringUtils.EMPTY);
        this.tfUrl2Report.setText(StringUtils.EMPTY);
        this.setVisible(false);
    }

    @Override
    public void notifyChanges(Host obj) {
        if(obj == this.host){
            this.refresh();
        }
    }
}
