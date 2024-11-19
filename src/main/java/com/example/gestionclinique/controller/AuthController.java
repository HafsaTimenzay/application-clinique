package com.example.gestionclinique.controller;

import com.example.gestionclinique.model.Compte;
import com.example.gestionclinique.model.DAO.CompteDAO;
import com.example.gestionclinique.model.Patient;
import com.example.gestionclinique.model.DAO.PatientDAO;
import com.example.gestionclinique.model.util.ConnectionUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;  // Import Node class
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
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
    private PatientDAO patientDAO;

    public AuthController() {
        try {
            // Initialize database connection
            Connection connection = ConnectionUtil.getConnection();
            compteDAO = new CompteDAO(connection);
            patientDAO = new PatientDAO(connection);
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

        return  true;
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
            // Authenticate against the database
            if (compteDAO.authenticate(emailField.getText(),  passwordField.getText())) {
                try {
                    System.out.println(compteDAO.getUserType(emailField.getText(), passwordField.getText()));
                    if(compteDAO.getUserType(emailField.getText(), passwordField.getText()).equals("Patient")){
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/patient/main-patient.fxml"));
                        Parent root = loader.load();

                        // Get the PatientController instance
                        PatientController patientController = loader.getController();

                        // Call the loadProfilePage() method to display the profile page
                        patientController.loadDashboard();

                        // Switch to the main-patient scene
                        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        currentStage.setScene(scene);
                        currentStage.setMaximized(true);
                        currentStage.setTitle("Patient Dashboard");
                        currentStage.show();
                    }

                    if(compteDAO.getUserType(emailField.getText(), passwordField.getText()).equals("Medecin")){
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/medecin/main-medecin.fxml"));
                        Parent root = loader.load();

                        // Get the MedecinController instance
                        MedecinController medecinController = loader.getController();

                        // Call the loadProfilePageMe() method to display the profile page
                        medecinController.loadDashboard();

                        // Switch to the main-medecin scene
                        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        currentStage.setScene(scene);
                        currentStage.setMaximized(true);
                        currentStage.setTitle("Medecin Dashboard");
                        currentStage.show();

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                showAlert("User not Found", "Password or email is incorrect");

            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while checking credentials.");

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
    public void handleSignupSubmit(ActionEvent event) {

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

                Patient patient = new Patient();
                System.out.println(compte.getIdCompte());
                patient.setCompteId(compte.getIdCompte());
                patient.setNom(firstName.getText());
                patient.setPrenom(lastName.getText());



                try {
                    // Insert the new compte into the database
                    compteDAO.createCompte(compte);
                    patientDAO.InsertPatient(patient);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/patient/main-patient.fxml"));
                    Parent root = loader.load();

                    // Get the PatientController instance
                    PatientController patientController = loader.getController();

                    // Call the loadProfilePage() method to display the profile page
                    patientController.loadProfilePage();

                    // Switch to the main-patient scene
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    currentStage.setScene(scene);
                    currentStage.setMaximized(true);
                    currentStage.setTitle("Patient Dashboard");
                    currentStage.show();
                } catch (SQLException e) {
                    e.printStackTrace();
                    showAlert("Error", "An error occurred while creating the account.");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }

    }


}
