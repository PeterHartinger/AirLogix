
package com.flightDomain.flightbook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

    private static Stage primaryStage;
    private static double savedWidth = 964.0;
    private static double savedHeight = 744.0;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        showMainView();
        primaryStage.show();
    }

    public static void navigateTo(String fxmlFile) {
        try {
            savedWidth = primaryStage.getWidth();
            savedHeight = primaryStage.getHeight();

            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(fxmlFile));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setWidth(savedWidth);
            primaryStage.setHeight(savedHeight);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showMainView() {
        navigateTo("view/MainView.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
