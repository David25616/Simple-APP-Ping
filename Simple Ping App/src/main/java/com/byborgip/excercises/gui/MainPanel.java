package com.byborgip.excercises.gui;

import com.byborgip.excercises.app.ApplicationContext;
import com.byborgip.excercises.model.Host;
import com.byborgip.excercises.services.HostService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MainPanel extends BorderPane implements HostObserver{
    private final Stage stage;
    private final HostDialog addHostDialog;

    private final ListView<Host> listViewHost;

    private final HostPane hostPane;

    private final ConfigDialog configDialog;

    public MainPanel(Stage stage){
        super();
        this.stage = stage;
        this.addHostDialog = new HostDialog(this);
        this.listViewHost = new ListView<>();
        listViewHost.getStyleClass().add("list-view-host");
        this.hostPane = new HostPane();
        configDialog = new ConfigDialog();
        initializeMenu();

        HBox center = new HBox(20);
        center.getStyleClass().add("main-content");
        initializeLeft(center);
        initializeRight(center);
        this.setCenter(center);
        notifyChange();
    }

    private void initializeRight(HBox center) {
        VBox right = new VBox(5);

        Text infoTitle=new Text(ApplicationContext.bundle.getString("application.gui.title.host.info"));
        infoTitle.getStyleClass().add("text-section-title");
        right.getChildren().addAll(infoTitle,hostPane);
        center.getChildren().add(right);

    }

    private void initializeLeft(HBox center) {

        VBox left = new VBox(5);

        Text hostTitle = new Text(ApplicationContext.bundle.getString("application.gui.title.host"));
        hostTitle.getStyleClass().add("text-section-title");

        HBox btns = new HBox(5);

        Button btnAdd = new Button(ApplicationContext.bundle.getString("application.gui.btn.add.host"));
        Button btnModify = new Button(ApplicationContext.bundle.getString("application.gui.btn.modify.host"));
        Button btnRemove = new Button(ApplicationContext.bundle.getString("application.gui.btn.remove.host"));
        btns.getChildren().addAll(btnAdd,btnModify,btnRemove);

        btnAdd.getStyleClass().add("button-host");
        btnModify.getStyleClass().add("button-host");
        btnRemove.getStyleClass().add("button-host");

        btnModify.setDisable(true);
        btnRemove.setDisable(true);

        left.getChildren().addAll(hostTitle, listViewHost, btns);

        center.getChildren().add(left);

        btnAdd.setOnAction(event -> addHostDialog.showCreate());
        btnModify.setOnAction(event -> addHostDialog.showModify(listViewHost.getSelectionModel().getSelectedItem()));

        btnRemove.setOnAction(event -> {
            HostService.getInstance().deleteHost(listViewHost.getSelectionModel().getSelectedItem());
            this.notifyChange();
        });

        listViewHost.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnRemove.setDisable(newValue==null);
            btnModify.setDisable(newValue==null);
            hostPane.show(newValue);
        });

    }

    private void initializeMenu() {
        MenuBar menuBar = new MenuBar();
        menuBar.getStyleClass().add("menu-bar");
        Menu fileMenu = new Menu(ApplicationContext.bundle.getString("applications.gui.file.menu"));
        MenuItem configMenuItem = new MenuItem(ApplicationContext.bundle.getString("applications.gui.config.menuitem"));
        MenuItem exitMenuItem = new MenuItem(ApplicationContext.bundle.getString("applications.gui.exit.menuitem"));
        fileMenu.getItems().addAll(configMenuItem, exitMenuItem);
        menuBar.getMenus().add(fileMenu);
        this.setTop(menuBar);

        configMenuItem.setOnAction(event -> configDialog.showDialog());
        exitMenuItem.setOnAction(event -> stage.close());

    }

    @Override
    public void notifyChange() {
        ObservableList<Host> items = FXCollections.observableArrayList(HostService.getInstance().getAll());
        this.listViewHost.setItems(items);
    }
}
