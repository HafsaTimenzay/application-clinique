package com.example.gestionclinique.controller;

import com.example.gestionclinique.model.Compte;
import com.example.gestionclinique.model.DAO.CompteDAO;
import com.example.gestionclinique.model.util.ConnectionUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;  // Import Node class
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AuthController {

    @FXML
    private TextField emailField;  // Email field
    @FXML
    private PasswordField passwordField;  // Password field
    @FXML
    private PasswordField password;
    @FXML
    private RadioButton medecinRadio;
    @FXML
    private RadioButton patientRadio;
    @FXML
    private TextField email;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;



    private ToggleGroup userTypeGroup;


    // String email, String username, String password, String confirmPassword, String typeUtilisateur

    private CompteDAO compteDAO;

    public AuthController() {
        try {
            // Initialize database connection
            Connection connection = ConnectionUtil.getConnection();
            compteDAO = new CompteDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to validate the email and password
    public boolean validateCredentials(String email, String password) {

        // Validate email
        if (email == null || email.isEmpty() || !email.contains("@") || !email.contains(".")) {
            showAlert("Invalid Email", "Please enter a valid email address.");
            return false;
        }

        // Validate password
        if (password == null || password.isEmpty()) {
            showAlert("Invalid Password", "Password cannot be empty.");
            return false;
        }

        try {
            // Authenticate against the database
            if (compteDAO.authenticate(email, password)) {
                return true;
            } else {
                showAlert("User not Found", "Password or email is incorrect");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while checking credentials.");
            return false;
        }
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
            if (validateCredentials(emailField.getText(), passwordField.getText())) {
                // Load the main view (patient or doctor dashboard)
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/patient/main-patient.fxml"));
                Parent root = loader.load();

                // Get the controller of the main view
                // Handle dashboard loading based on user type (Medecin or Patient)
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                currentStage.setScene(scene);
                currentStage.setMaximized(true);
                currentStage.setTitle("Gestion Clinique - Dashboard");
                currentStage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Signup button handler
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




    // Handle sign-up form submission
    public void handleSignupSubmit() {

        ToggleGroup userTypeGroup = new ToggleGroup();
        medecinRadio.setToggleGroup(userTypeGroup); // Error occurs here
        patientRadio.setToggleGroup(userTypeGroup);

        RadioButton selectedButton = (RadioButton) userTypeGroup.getSelectedToggle();
        String typeUtilisateur = selectedButton != null ? selectedButton.getText() : null;

        if(email.getText() == null || email.getText().isEmpty() || password.getText() == null || password.getText().isEmpty()
        || firstName.getText() == null || firstName.getText().isEmpty() || lastName.getText() == null || lastName.getText().isEmpty()
                || typeUtilisateur == null || typeUtilisateur.isEmpty() ) {
            showAlert(null, "Please fill all the fields");
        }else{
            if (validateCredentials(email.getText(), password.getText())){
                // Create a new Compte object
                Compte compte = new Compte();
                compte.setEmail(email.getText());
                compte.setFirstName(firstName.getText());
                compte.setLastName(lastName.getText());
                compte.setPassword(password.getText());
                compte.setTypeUtilisateur(typeUtilisateur);

                try {
                    // Insert the new compte into the database
                    compteDAO.createCompte(compte);
                    showAlert("Success", "Account successfully created.");
                } catch (SQLException e) {
                    e.printStackTrace();
                    showAlert("Error", "An error occurred while creating the account.");
                }
            }else {
                showAlert(null, "enter correct email");
            }

        }

    }
}
