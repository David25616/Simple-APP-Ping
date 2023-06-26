package com.byborgip.excercises.gui;

import com.byborgip.excercises.app.ApplicationContext;
import com.byborgip.excercises.commands.CommandResult;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.apache.commons.lang3.StringUtils;

import java.time.format.DateTimeFormatter;

public abstract  class CommandResultPane<T extends CommandResult> extends GridPane {
    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private Label lbDate = new Label(ApplicationContext.bundle.getString("application.gui.result.date"));
    protected Text txtDate = new Text();

    public CommandResultPane(){
        this.setVgap(10);
        this.setHgap(15);
        this.add(lbDate, 0, 0);
        this.add(txtDate, 1, 0);
    }

    public void clear(){
        this.txtDate.setText(StringUtils.EMPTY);
        doClear();
    }

    protected abstract void doClear();
    protected abstract void doUpdate(T result);

    public final void update(T result){
        if(result==null){
            this.clear();
            return;
        }
        this.txtDate.setText(result.getDateTime().format(formatter));
        this.doUpdate(result);
    }
}
