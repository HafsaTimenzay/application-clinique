package com.example.gestionclinique.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AuthController{

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    // Method to handle login button action
    @FXML
    private void handleLoginAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Simple validation example
        if (username.equals("user") && password.equals("pass")) {
            navigateToHomePage(event);
        } else {
            showAlert("Login Failed", "Invalid username or password.");
        }
    }

    // Method to navigate to signup page
    @FXML
    private void navigateToSignup(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("@../view/Authentification/signupView.fxmlx"));
            Parent signupPage = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(signupPage));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to navigate to home page after successful login
    private void navigateToHomePage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/patient/home.fxml"));
            Parent homePage = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(homePage));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to show alert dialog
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
