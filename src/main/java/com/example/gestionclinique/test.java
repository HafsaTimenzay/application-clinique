package com.example.gestionclinique;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class test extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the main layout (main-patient.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/patient/main-patient.fxml"));
            Parent root = loader.load();

            // Create the scene and set the stage
            Scene scene = new Scene(root, 1200, 650);
            primaryStage.setTitle("Dynamic Content Navigation with FXML");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
