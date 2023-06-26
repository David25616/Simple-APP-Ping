package com.byborgip.excercises.gui;

import com.byborgip.excercises.app.ApplicationContext;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {


    private static final GUI gui = new GUI();
    public static void startApp(String... args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(ApplicationContext.bundle.getString("application.gui.title"));
        MainPanel mainPanel = new MainPanel(stage);
        Scene scene = new Scene(mainPanel, Double.valueOf(ApplicationContext.bundle.getString("application.gui.width")), Double.valueOf(ApplicationContext.bundle.getString("application.gui.height")));
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        stage.show();
    }
}
