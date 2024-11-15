package com.example.gestionclinique.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;  // Import Node class
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class AuthController {

    @FXML
    private void handleSignupClick(ActionEvent event) {
        try {
            // Load the signup FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/Authentification/signup-view.fxml"));
            Parent root = loader.load();

            // Get the current stage (the login stage)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene (signup page)
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.setTitle("Sign-Up");
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
