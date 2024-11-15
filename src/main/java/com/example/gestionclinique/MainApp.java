package com.example.gestionclinique;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Authentification/login-view.fxml"));
        Parent root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root, 789, 494);

        // Configure and show the stage
        primaryStage.setTitle("Gestion Clinique");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
