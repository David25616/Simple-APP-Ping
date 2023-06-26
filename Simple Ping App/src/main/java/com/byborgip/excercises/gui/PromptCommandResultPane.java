package com.byborgip.excercises.gui;

import com.byborgip.excercises.app.ApplicationContext;
import com.byborgip.excercises.commands.CommandResult;
import com.byborgip.excercises.commands.PromptCommandResult;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.apache.commons.lang3.StringUtils;

import java.time.format.DateTimeFormatter;

public class PromptCommandResultPane extends CommandResultPane<PromptCommandResult> {



    private Label lbRes = new Label(ApplicationContext.bundle.getString("application.gui.result.result"));
    private TextArea resultTa = new TextArea();

    public PromptCommandResultPane(){
        super();
        this.add(lbRes, 0, 1);
        this.add(resultTa, 1, 1);
        resultTa.setEditable(false);
        resultTa.setPrefRowCount(5);
        resultTa.getStyleClass().add("text-area-host");
        resultTa.textProperty().addListener((obs, oldValue, newValue) -> {
            resultTa.setScrollTop(Double.MAX_VALUE);
        });
    }

    @Override
    protected synchronized void doUpdate(PromptCommandResult result){
        updateTxtDate(result.getDateTime().format(formatter));
        updateResultTa(result.getResult());
        resultTa.setScrollTop(Double.MAX_VALUE);
    }

    @Override
    public synchronized void doClear(){
        updateTxtDate(StringUtils.EMPTY);
        updateResultTa(StringUtils.EMPTY);
    }

    private synchronized void updateTxtDate(String val){
        txtDate.setText(val);
    }
    private synchronized void updateResultTa(String val){
        resultTa.setText(val);
    }
}
