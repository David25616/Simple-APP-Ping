package com.byborgip.excercises.gui;

import com.byborgip.excercises.app.Application;
import com.byborgip.excercises.app.ApplicationContext;
import com.byborgip.excercises.model.Host;
import com.byborgip.excercises.services.HostService;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

public class HostDialog extends Dialog<Void> {

    private Host host2Modify;
    private final HostObserver obs;
    private final AddHostDialogPane dialogPaneContent;
    public HostDialog(HostObserver obs){
        super();
        this.obs = obs;
        this.setTitle(ApplicationContext.bundle.getString("application.gui.add.host.dialog.title"));

        this.getDialogPane().setContent(dialogPaneContent = new AddHostDialogPane());

        this.getDialogPane().getScene().getWindow().setOnCloseRequest(event -> {
            dialogPaneContent.clear();
        });

        this.getDialogPane().getScene().getStylesheets().add("styles.css");
    }


    public void showModify(Host host){
        this.host2Modify =host;
        setTitle(ApplicationContext.bundle.getString("application.gui.modify.host.dialog.title"));
        this.refresh();
        this.showAndWait();
    }

    public void showCreate(){
        this.host2Modify =null;
        this.refresh();
        this.showAndWait();
    }

    public void refresh(){
        if(host2Modify ==null){
            dialogPaneContent.clear();
            return;
        }
        dialogPaneContent.setHost(host2Modify);
    }

    private void closeDialog(){
        this.close();
    }
    public class AddHostDialogPane extends GridPane {

        private final TextField nameTextField;
        private final NumericTextField tcpConnectionTimeoutField;

        private final TextField url2ReportField;

        private final Text textId;


        public boolean validate(){
            return StringUtils.isNoneBlank(this.nameTextField.getText(), this.tcpConnectionTimeoutField.getText(), this.url2ReportField.getText());
        }

        public AddHostDialogPane(){
            this.setVgap(10);
            this.setHgap(10);

            Label idLabel = new Label(ApplicationContext.bundle.getString("application.gui.host.id"));
            textId = new Text();

            Label nameLabel = new Label(ApplicationContext.bundle.getString("application.gui.host.name"));
            nameTextField = new TextField();
            nameTextField.getStyleClass().add("text-field");

            Label tcpConnectionTimeoutLabel = new Label(ApplicationContext.bundle.getString("application.gui.tcp.timeout.host"));
            tcpConnectionTimeoutField = new NumericTextField();
            tcpConnectionTimeoutField.getStyleClass().add("text-field");

            Label url2ReportLabel = new Label(ApplicationContext.bundle.getString("application.gui.host.url2report"));
            url2ReportField = new TextField();

            url2ReportField.getStyleClass().add("text-field");

            Button submitButton = new Button(ApplicationContext.bundle.getString("application.gui.host.save"));
            submitButton.getStyleClass().add("button-host");

            this.add(idLabel, 0, 0);
            this.add(textId, 1, 0);
            this.add(nameLabel, 0, 1);
            this.add(nameTextField, 1, 1);
            this.add(tcpConnectionTimeoutLabel, 0, 2);
            this.add(tcpConnectionTimeoutField, 1, 2);
            this.add(url2ReportLabel, 0, 3);
            this.add(url2ReportField, 1, 3);
            this.add(submitButton, 0, 4, 4, 1);

            submitButton.setOnAction(event -> {
                if(!validate()){
                    return;
                }

                if(host2Modify==null){
                    Host host = new Host(this.nameTextField.getText(), Integer.valueOf(this.tcpConnectionTimeoutField.getText()), this.url2ReportField.getText());
                    HostService.getInstance().addHost(host);
                }
                else{
                    host2Modify.setName(this.nameTextField.getText());
                    host2Modify.setTcpConnectionTimeOut(Integer.valueOf(this.tcpConnectionTimeoutField.getText()));
                    host2Modify.setUrl2Report(this.url2ReportField.getText());
                    Application.saveApp();
                }

                this.clear();
                obs.notifyChange();
                Stage stage = (Stage) submitButton.getScene().getWindow();
                stage.close();
            });
        }

        public void clear() {
            this.nameTextField.clear();
            this.tcpConnectionTimeoutField.clear();
            this.url2ReportField.clear();
        }

        public void setHost(Host host) {
            this.textId.setText(String.valueOf(host.getId()));
            this.nameTextField.setText(host.getName());
            this.tcpConnectionTimeoutField.setText(String.valueOf(host.getTcpConnectionTimeOut()));
            this.url2ReportField.setText(host.getUrl2Report());
        }
    }

}
