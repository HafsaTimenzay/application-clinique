package com.example.gestionclinique;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMedecin extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/medecin/main-medecin.fxml"));
        Parent root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root);
        primaryStage.setMaximized(true);

        // Configure and show the stage
        primaryStage.setTitle("Gestion Clinique");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
