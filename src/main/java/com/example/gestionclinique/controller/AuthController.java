package com.example.gestionclinique.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;  // Import Node class
import javafx.event.ActionEvent;

public class AuthController {

    @FXML
    private TextField usernameField;  // Email field
    @FXML
    private PasswordField passwordField;  // Password field
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
            currentStage.setTitle("Gestion Clinique Medical");
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Function to validate the email and password
    public boolean validateCredentials() {
        String email = usernameField.getText();
        String password = passwordField.getText();

        // Validate email
        if (email == null || email.isEmpty() || !email.contains("@") || !email.contains(".")) {
            showAlert("Invalid Email", "Please enter a valid email address.");
            return false;
        }

        // Validate password (just check if it's not empty for now)
        if (password == null || password.isEmpty()) {
            showAlert("Invalid Password", "Password cannot be empty.");
            return false;
        }

        if(!password.equals("p") || !email.equals("e@e.e")){

            showAlert("User not Found","password or email not crorrect "+!password.equals("p")+ !email.equals("e"));
            return false;
        }

        // You can add more complex checks here (like regex for email format, password strength, etc.)

        return true;  // Email and password are valid
    }

    // Show alert for invalid input
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Login button handler
    @FXML
    private void handleLoginClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/patient/profil-patient.fxml"));
            Parent root = loader.load();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.setMaximized(true);
            currentStage.setTitle("Sign-Up");
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
