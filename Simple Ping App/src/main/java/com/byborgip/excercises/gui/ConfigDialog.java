package com.byborgip.excercises.gui;

import com.byborgip.excercises.app.ApplicationContext;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

public class ConfigDialog  extends Dialog<Void> {

    private final ConfigPane pane;
    public ConfigDialog(){
        super();
        this.setTitle(ApplicationContext.bundle.getString("application.gui.config.title"));
        this.getDialogPane().setContent(pane = new ConfigPane(this));
        this.getDialogPane().getScene().getWindow().setOnCloseRequest(event -> {
        });
        this.getDialogPane().getScene().getStylesheets().add("styles.css");
    }

    public void closeDialog() {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }

    public void showDialog(){
        pane.refresh();
        showAndWait();
    }
}
