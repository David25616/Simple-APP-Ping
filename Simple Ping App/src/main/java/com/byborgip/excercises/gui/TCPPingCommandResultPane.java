package com.byborgip.excercises.gui;

import com.byborgip.excercises.app.ApplicationContext;
import com.byborgip.excercises.commands.CommandResult;
import com.byborgip.excercises.commands.TCPPingCommandResult;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.apache.commons.lang3.StringUtils;

import java.time.format.DateTimeFormatter;

public class TCPPingCommandResultPane extends CommandResultPane<TCPPingCommandResult> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private final Label lbHTTPResultCode = new Label(ApplicationContext.bundle.getString("application.http.res.code"));
    private final Text txtHttpCode = new Text();
    private final Label lbResTime = new Label(ApplicationContext.bundle.getString("application.http.res.time"));
    private final Text txtRespTime = new Text();
    public TCPPingCommandResultPane(){
        super();
        this.add(lbHTTPResultCode, 0, 1);
        this.add(txtHttpCode, 1, 1);

        this.add(lbResTime, 0, 2);
        this.add(txtRespTime, 1, 2);

    }

    @Override
    protected void doClear() {
        txtHttpCode.setText(StringUtils.EMPTY);
        txtRespTime.setText(StringUtils.EMPTY);
    }

    @Override
    protected void doUpdate(TCPPingCommandResult result) {
        txtHttpCode.setText(String.valueOf(result.getHttpStatusCode()));
        txtRespTime.setText(String.valueOf(result.getResponseTime()));
    }
}
